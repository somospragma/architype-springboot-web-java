package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.secrets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.operationsandexecution.loansanddeposits.domain.exceptions.ApiException;
import com.pragma.operationsandexecution.loansanddeposits.domain.exceptions.StandardRestException;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.PartyDataReference;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.response.PartyDataReferenceResponseModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.IAuthenticationPort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.dto.PartyDataReferenceDto;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.generic.IGenericRestClientPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.model.HttpRequestConfig;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.model.response.EntrustErrorResponse;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.model.response.PartyDataReferenceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.LAYER_INFRASTRUCTURE_DATA_PROVIDER_REST_GET_TOKEN;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.PATH_ENTRUST_CREATE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.REST_RESPONSE_SUCCESS;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.UTILITY_REST_ENTRUST_URL_YML;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.UTILITY_REST_HEADER_ACCEPT;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.UTILITY_REST_HEADER_APPLICATION_ID;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.UTILITY_REST_HEADER_CONTENT_TYPE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.UTILITY_SECRET_NAME_ENTRUST;

@Slf4j
@Component
@Profile("local")
@Primary
@RequiredArgsConstructor
public class EntrustAuthenticationLocalAdapter implements IAuthenticationPort {


    public PartyDataReferenceResponseModel getTokenAuthenticationEntrust(String userName, String transactionId) {
        return new PartyDataReferenceResponseModel("local", "test", "last");
    }

    public void createUserEntrust(PartyDataReference[] partyDataReferenceModel, String transactionId) {
        log.info("Creating user in Entrust with transactionId: {}", transactionId);

    }

}
