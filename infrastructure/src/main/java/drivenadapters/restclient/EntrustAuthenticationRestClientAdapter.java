package drivenadapters.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;

import drivenadapters.restclient.dto.PartyDataReferenceDto;
import drivenadapters.restclient.generic.IGenericRestClientPort;
import drivenadapters.restclient.model.HttpRequestConfig;
import drivenadapters.restclient.model.response.EntrustErrorResponse;
import drivenadapters.restclient.model.response.PartyDataReferenceResponseDto;
import drivenadapters.secrets.IEntrustSecret;
import com.pragma.loansanddeposits.exceptions.ApiException;
import com.pragma.loansanddeposits.exceptions.StandardRestException;
import com.pragma.loansanddeposits.model.PartyDataReference;
import com.pragma.loansanddeposits.model.response.PartyDataReferenceResponseModel;
import com.pragma.loansanddeposits.port.out.IAuthenticationPort;
import com.pragma.loansanddeposits.port.out.ILoggerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

import static constants.InfrastructureConstants.LAYER_INFRASTRUCTURE_DATA_PROVIDER_REST_GET_TOKEN;
import static constants.InfrastructureConstants.PATH_ENTRUST_CREATE;
import static constants.InfrastructureConstants.REST_RESPONSE_SUCCESS;
import static constants.InfrastructureConstants.UTILITY_REST_ENTRUST_URL_YML;
import static constants.InfrastructureConstants.UTILITY_REST_HEADER_ACCEPT;
import static constants.InfrastructureConstants.UTILITY_REST_HEADER_APPLICATION_ID;
import static constants.InfrastructureConstants.UTILITY_REST_HEADER_CONTENT_TYPE;
import static constants.InfrastructureConstants.UTILITY_SECRET_NAME_ENTRUST;

@Component
@RequiredArgsConstructor
public class EntrustAuthenticationRestClientAdapter implements IAuthenticationPort {

    @Value(UTILITY_REST_ENTRUST_URL_YML)
    private String entrustUrl;

    private final IGenericRestClientPort iGenericRestClientPort;
    private final ObjectMapper objectMapper;
    private final Environment environment;
    private final ILoggerPort loggerService;
    private final IEntrustSecret iEntrustSecret;

    public PartyDataReferenceResponseModel getTokenAuthenticationEntrust(String userName, String transactionId) {
        String url = entrustUrl + userName;
        Map<String, String> headers = Map.of(UTILITY_REST_HEADER_ACCEPT, MediaType.ALL_VALUE,
                UTILITY_REST_HEADER_CONTENT_TYPE, MediaType.ALL_VALUE,
                UTILITY_REST_HEADER_APPLICATION_ID, iEntrustSecret.retrieveAndParseSecret(UTILITY_SECRET_NAME_ENTRUST)
                        .getApplicationId());
        HttpRequestConfig<Object> requestConfig = new HttpRequestConfig<>(HttpMethod.POST, url, headers, null);
        ResponseEntity<PartyDataReferenceResponseDto> responseEntity;
        try{
            responseEntity = iGenericRestClientPort.sendRequestAndReceiveResponse(requestConfig,
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

    public void createUserEntrust(PartyDataReference[] partyDataReferenceModel, String transactionId) {
        String url = environment.getProperty(UTILITY_REST_ENTRUST_URL_YML) + PATH_ENTRUST_CREATE;
        Map<String, String> headers = Map.of(UTILITY_REST_HEADER_ACCEPT, MediaType.APPLICATION_JSON_VALUE,
                UTILITY_REST_HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpRequestConfig<Object> requestConfig = new HttpRequestConfig<>(HttpMethod.POST, url, headers,
                objectMapper.convertValue(partyDataReferenceModel, PartyDataReferenceDto[].class));

        try{
            iGenericRestClientPort.sendRequestAndReceiveResponse(requestConfig, Object.class,
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

}
