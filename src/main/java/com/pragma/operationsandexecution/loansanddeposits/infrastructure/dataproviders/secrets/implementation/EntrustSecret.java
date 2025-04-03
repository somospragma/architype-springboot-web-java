package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.secrets.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.operationsandexecution.crosscutting.exceptions.ApiException;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerService;
import com.pragma.operationsandexecution.crosscutting.secrets.ISecretService;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.secrets.dto.EntrustCredentials;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.secrets.interfaces.IEntrustSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.*;

@Component
@RequiredArgsConstructor
public class EntrustSecret implements IEntrustSecret {

    private final ISecretService secretService;
    private final ObjectMapper objectMapper;
    private final ILoggerService loggerService;

    @Override
    public EntrustCredentials retrieveAndParseSecret(String secretName) {
        try {
            String secretJson = secretService.getSecret(secretName);
            return objectMapper.readValue(secretJson, EntrustCredentials.class);
        } catch (JsonProcessingException exception) {
            loggerService.logError(secretName, LAYER_INFRASTRUCTURE_DATA_PROVIDER_GET_SECRET,
                    SECRET_PARSER_ERROR_CATCH, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_SECRET_PARSER_ERROR);
        }
    }

}
