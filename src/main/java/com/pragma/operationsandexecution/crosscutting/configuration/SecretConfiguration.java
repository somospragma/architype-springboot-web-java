package com.pragma.operationsandexecution.crosscutting.configuration;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

import static com.pragma.operationsandexecution.crosscutting.constants.common.SecretConstants.*;

/**
 * Configuración para inyectar secretos desde Azure Key Vault.
 * <p>
 * Esta clase define beans que obtienen secretos específicos y los exponen como beans de Spring.
 * </p>
 */
@RequiredArgsConstructor
@Configuration
public class SecretConfiguration {

    private final Environment environment;

    public KeyVaultSecret getSecretClient(String secretName) {
        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(Objects.requireNonNull(environment.getProperty(UTILITY_SECRET_URI)))
                .credential(buildClientCredential())
                .buildClient();
        return secretClient.getSecret(secretName);
    }

    @Bean
    public ClientSecretCredential buildClientCredential() {
        return new ClientSecretCredentialBuilder()
                .clientId(environment.getProperty(UTILITY_SECRET_CLIENT_ID_YML))
                .clientSecret(environment.getProperty(UTILITY_SECRET_CLIENT_SECRET_YML))
                .tenantId(environment.getProperty(UTILITY_SECRET_TENANT_ID_YML))
                .build();
    }

}
