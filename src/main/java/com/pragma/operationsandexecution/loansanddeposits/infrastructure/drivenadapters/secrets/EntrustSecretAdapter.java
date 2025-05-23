package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.secrets;

import com.azure.identity.ClientSecretCredential;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.operationsandexecution.loansanddeposits.domain.exceptions.ApiException;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.secrets.dto.EntrustCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.LAYER_INFRASTRUCTURE_DATA_PROVIDER_GET_SECRET;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.MESSAGE_KEY_SECRET_PARSER_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.SECRET_PARSER_ERROR_CATCH;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SecretConstants.UTILITY_SECRET_URI;

@Component
@RequiredArgsConstructor
public class EntrustSecretAdapter implements IEntrustSecret {

    @Value(UTILITY_SECRET_URI)
    private String vaultUri;

    private final ObjectMapper objectMapper;

    private final ClientSecretCredential clientSecretCredential;

    private final ILoggerPort loggerService;

    @Override
    public EntrustCredentials retrieveAndParseSecret(String secretName) {
        try {
            String secretJson = this.getSecretClient(secretName).getValue();
            return objectMapper.readValue(secretJson, EntrustCredentials.class);
        } catch (JsonProcessingException exception) {
            loggerService.logError(secretName, LAYER_INFRASTRUCTURE_DATA_PROVIDER_GET_SECRET,
                    SECRET_PARSER_ERROR_CATCH, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_SECRET_PARSER_ERROR);
        }
    }

    public KeyVaultSecret getSecretClient(String secretName) {
        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(vaultUri)
                .credential(clientSecretCredential)
                .buildClient();
        return secretClient.getSecret(secretName);
    }

}
