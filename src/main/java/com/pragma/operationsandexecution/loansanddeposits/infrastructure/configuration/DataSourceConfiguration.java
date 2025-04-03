package com.pragma.operationsandexecution.loansanddeposits.infrastructure.configuration;

import com.pragma.operationsandexecution.crosscutting.logging.ILoggerService;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static com.pragma.operationsandexecution.crosscutting.constants.common.CommonConstants.PACKAGE_ENTITY_MANAGERS_ENTITIES;
import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.*;

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
    private final ILoggerService loggerService;

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

    /**
     * Configuración del {@link DataSource} para Azure SQL.
     *
     * @return una instancia configurada de {@link DataSource}
     */
    @Bean(name = BEAN_AZURE_SQL_DATA_SOURCE)
    @Primary
    @ConfigurationProperties(prefix = CONFIGURATION_PROPERTIES_SPRING_DATA_SOURCE)
    public DataSource azureSqlDataSource(DataSourceProperties properties) {
        try {
            return properties.initializeDataSourceBuilder().build();
        } catch (Exception exception) {
            loggerService.logInfo(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_CONFIGURATION_AZURE_SQL_DATA_SOURCE,
                    COSMOS_CREATE_ERROR, exception.getMessage());
            throw new RuntimeException(COSMOS_CREATE_CLIENT, exception);
        }
    }

    /**
     * Configura el {@link LocalContainerEntityManagerFactoryBean} para JPA.
     *
     * @param builder    el builder para crear el EntityManagerFactory
     * @param dataSource el {@link DataSource} configurado para Azure SQL
     * @return una instancia de {@link LocalContainerEntityManagerFactoryBean}
     */
    @Bean(name = BEAN_ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier(UTILITY_QUALIFIER_AZURE_SQL_DATA_SOURCE) DataSource dataSource) {
        try {
            return builder
                    .dataSource(dataSource)
                    .packages(PACKAGE_ENTITY_MANAGERS_ENTITIES)
                    .persistenceUnit(UTILITY_AZURE_SQL)
                    .build();
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_CONFIGURATION_ENTITY_MANAGER_FACTORY,
                    COSMOS_CREATE_ERROR, exception.getMessage());
            throw new RuntimeException(COSMOS_CREATE_CLIENT, exception);
        }
    }

    /**
     * Configura el {@link PlatformTransactionManager} para JPA.
     *
     * @param entityManagerFactory el {@link LocalContainerEntityManagerFactoryBean} configurado
     * @return una instancia de {@link PlatformTransactionManager}
     */
    @Bean(name = BEAN_TRANSACTION_MANAGER)
    public PlatformTransactionManager transactionManager(@Qualifier(UTILITY_QUALIFIER_ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        try {
            if (entityManagerFactory.getObject() == null) {
                loggerService.logError(BEAN_TRANSACTION_MANAGER, LAYER_INFRASTRUCTURE_CONFIGURATION_TRANSACTION_MANAGER,
                        TRANSACTION_MANAGER_ERROR, "");
                throw new IllegalStateException(TRANSACTION_MANAGER_ERROR_CATCH);
            }
            return new JpaTransactionManager(entityManagerFactory.getObject());
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_CONFIGURATION_TRANSACTION_MANAGER,
                    COSMOS_CREATE_ERROR, exception.getMessage());
            throw new RuntimeException(COSMOS_CREATE_CLIENT, exception);
        }
    }

}