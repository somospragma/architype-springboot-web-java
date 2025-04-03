package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.restclients.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.operationsandexecution.crosscutting.exceptions.ApiException;
import com.pragma.operationsandexecution.crosscutting.exceptions.StandardRestException;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerService;
import com.pragma.operationsandexecution.crosscutting.restclients.HttpRequestConfig;
import com.pragma.operationsandexecution.crosscutting.restclients.IGenericRestClient;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.PartyDataReferenceModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.response.PartyDataReferenceResponseModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.IPartyDataReferenceRestClientPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.restclients.response.EntrustErrorResponse;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.restclients.dto.PartyDataReferenceDto;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.restclients.response.PartyDataReferenceResponseDto;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.secrets.interfaces.IEntrustSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.*;

@Component
@RequiredArgsConstructor
public class EntrustPartyDataReferenceRestClient implements IPartyDataReferenceRestClientPort {

    private final IGenericRestClient iGenericRestClient;
    private final ObjectMapper objectMapper;
    private final Environment environment;
    private final ILoggerService loggerService;
    private final IEntrustSecret iEntrustSecret;

    public PartyDataReferenceResponseModel getTokenAuthenticationEntrust(String userName, String transactionId) {
        String url = environment.getProperty(UTILITY_REST_ENTRUST_URL_YML) + userName;
        Map<String, String> headers = Map.of(UTILITY_REST_HEADER_ACCEPT, MediaType.ALL_VALUE,
                UTILITY_REST_HEADER_CONTENT_TYPE, MediaType.ALL_VALUE,
                UTILITY_REST_HEADER_APPLICATION_ID, iEntrustSecret.retrieveAndParseSecret(UTILITY_SECRET_NAME_ENTRUST)
                        .getApplicationId());

        HttpRequestConfig<Object> requestConfig = createHttpRequestConfig(HttpMethod.GET, url, headers, null);
        ResponseEntity<PartyDataReferenceResponseDto> responseEntity = null;
        try{
            responseEntity = iGenericRestClient.sendRequestAndReceiveResponse(requestConfig,
                    PartyDataReferenceResponseDto.class, EntrustErrorResponse.class, transactionId);
        } catch (StandardRestException exception) {
            EntrustErrorResponse responseError = (EntrustErrorResponse) exception.getErrorResponseBody();
            throw new ApiException(exception.getStatusCodeAsHttpStatus(),
                    responseError.getCode(),
                    responseError.getMessage(),
                    null);
            // TODO: 12/03/25 Actualizar ApiException para poder actualizar este manejo de exception.
        }

        loggerService.logInfo(transactionId, LAYER_INFRASTRUCTURE_DATA_PROVIDER_REST_GET_TOKEN,
                REST_RESPONSE_SUCCESS, responseEntity.getBody());

        return objectMapper.convertValue(responseEntity.getBody(), PartyDataReferenceResponseModel.class);
    }

    public void createUserEntrust(PartyDataReferenceModel[] partyDataReferenceModel, String transactionId) {
        String url = environment.getProperty(UTILITY_REST_ENTRUST_URL_YML) + PATH_ENTRUST_CREATE;
        Map<String, String> headers = Map.of(UTILITY_REST_HEADER_ACCEPT, MediaType.APPLICATION_JSON_VALUE,
                UTILITY_REST_HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpRequestConfig<Object> requestConfig = createHttpRequestConfig(HttpMethod.POST, url, headers,
                objectMapper.convertValue(partyDataReferenceModel, PartyDataReferenceDto[].class));

        try{
            iGenericRestClient.sendRequestAndReceiveResponse(requestConfig, Object.class,
                    EntrustErrorResponse.class, transactionId);
        } catch (StandardRestException exception) {
            EntrustErrorResponse responseError = (EntrustErrorResponse) exception.getErrorResponseBody();
            throw new ApiException(exception.getStatusCodeAsHttpStatus(),
                    responseError.getCode(),
                    responseError.getMessage(),
                    null);
            // TODO: 12/03/25 Actualizar ApiException para poder actualizar este manejo de exception.
        }

        loggerService.logInfo(transactionId, LAYER_INFRASTRUCTURE_DATA_PROVIDER_REST_GET_TOKEN,
                REST_RESPONSE_SUCCESS, partyDataReferenceModel);
    }

    private HttpRequestConfig<Object> createHttpRequestConfig(HttpMethod method, String url, Map<String, String> headers,
                                                            Object body) {
        return new HttpRequestConfig<>(method, url, headers, body);
    }

}
