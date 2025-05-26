package drivenadapters.secrets.config;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static constants.SecretConstants.UTILITY_SECRET_URI;
import static constants.SecretConstants.UTILITY_SECRET_CLIENT_ID_YML;
import static constants.SecretConstants.UTILITY_SECRET_CLIENT_SECRET_YML;
import static constants.SecretConstants.UTILITY_SECRET_TENANT_ID_YML;

@RequiredArgsConstructor
@Configuration
public class SecretConfiguration {

    @Value(UTILITY_SECRET_URI)
    private String vaultUri;

    @Value(UTILITY_SECRET_CLIENT_ID_YML)
    private String clientId;

    @Value(UTILITY_SECRET_CLIENT_SECRET_YML)
    private String clientSecret;

    @Value(UTILITY_SECRET_TENANT_ID_YML)
    private String tenantId;

    public KeyVaultSecret getSecretClient(String secretName) {
        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(vaultUri)
                .credential(buildClientCredential())
                .buildClient();
        return secretClient.getSecret(secretName);
    }

    @Bean
    public ClientSecretCredential buildClientCredential() {
        return new ClientSecretCredentialBuilder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .tenantId(tenantId)
                .build();
    }

}
