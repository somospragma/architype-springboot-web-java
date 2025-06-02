package com.pragma.loansanddeposits.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

/**
 * Configuración para Azure Blob Storage.
 * <p>
 * Define beans relacionados con la configuración de Blob Storage.
 * </p>
 */
@Configuration
public class BlobStorageConfiguration {

    @Value("${azure.storage.blob.connection-string}")
    private String connectionString;

    @Value("${azure.storage.blob.container-name}")
    private String containerName;

    /**
     * Crea un {@link BlobContainerClient} para interactuar con el contenedor de blobs.
     *
     * @return una instancia de {@link BlobContainerClient}
     */
    @Bean
    public BlobContainerClient blobContainerClient() {
        BlobContainerClientBuilder builder = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName);
        return builder.buildClient();
    }

}
