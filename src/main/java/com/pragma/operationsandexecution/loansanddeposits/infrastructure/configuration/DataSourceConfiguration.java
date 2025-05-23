package com.pragma.operationsandexecution.loansanddeposits.infrastructure.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerPort;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.LAYER_INFRASTRUCTURE_CONFIGURATION_MONGO_CLIENT;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.MONGO_CREATE_CLIENT;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.MONGO_CREATE_ERROR;

/**
 * Configuración de las fuentes de datos para la aplicación.
 * <p>
 * Esta clase configura tanto las conexiones a Azure Cosmos DB (NoSQL) como a Azure SQL (SQL),
 * definiendo los beans necesarios para interactuar con ambas bases de datos.
 * </p>
 *
 * <p>
 * Además, configura las entidades JPA y los repositorios de Cosmos DB, asegurando que
 * las operaciones de persistencia sean manejadas adecuadamente.
 * </p>
 *
 * <p>
 * Esta configuración sigue los principios SOLID para mantener una arquitectura limpia y
 * mantenible.
 * </p>
 *
 * @Configuration indica a Spring que esta clase contiene definiciones de beans.
 * @EnableMongoRepositories habilita los repositorios de Cosmos DB en el paquete especificado.
 */
@Configuration
@EnableMongoRepositories
@EnableTransactionManagement
@RequiredArgsConstructor
public class DataSourceConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;
    private final ILoggerPort loggerService;

    /**
     * Configura el MongoClient para conectarse a Cosmos DB con la API de MongoDB.
     *
     * @return una instancia de MongoClient
     */
    @Override
    @NonNull
    @Bean
    public MongoClient mongoClient() {
        try {
            ConnectionString connectionString = new ConnectionString(mongoUri);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            return MongoClients.create(settings);
        } catch (Exception exception) {
            loggerService.logInfo(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_CONFIGURATION_MONGO_CLIENT,
                    MONGO_CREATE_ERROR, exception.getMessage());
            throw new RuntimeException(MONGO_CREATE_CLIENT, exception);
        }
    }

    @Override
    @NonNull
    protected String getDatabaseName() {
        return mongoDatabase;
    }

}
