package com.pragma.loansanddeposits;

import com.azure.identity.ClientSecretCredential;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.loansanddeposits.exceptions.ApiException;
import com.pragma.loansanddeposits.model.EntrustCredentials;
import com.pragma.loansanddeposits.port.out.IEntrustSecret;
import com.pragma.loansanddeposits.port.out.ILoggerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.pragma.loansanddeposits.constant.SecretConstants.LAYER_INFRASTRUCTURE_DATA_PROVIDER_GET_SECRET;
import static com.pragma.loansanddeposits.constant.SecretConstants.MESSAGE_KEY_SECRET_PARSER_ERROR;
import static com.pragma.loansanddeposits.constant.SecretConstants.SECRET_PARSER_ERROR_CATCH;
import static com.pragma.loansanddeposits.constant.SecretConstants.UTILITY_SECRET_URI;


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
            throw new ApiException(500, "500 INTERNAL_SERVER_ERROR", MESSAGE_KEY_SECRET_PARSER_ERROR);
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
