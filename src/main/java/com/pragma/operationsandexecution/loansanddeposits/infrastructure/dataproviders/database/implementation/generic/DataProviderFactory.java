package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.generic;

import com.pragma.operationsandexecution.crosscutting.logging.ILoggerService;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.nosql.NoSqlDataProvider;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.sql.SqlDataProvider;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic.IDataProvider;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.*;

/**
 * Fábrica para obtener implementaciones de {@link IDataProvider} basadas en el tipo de base de datos.
 * <p>
 * Esta clase proporciona métodos para crear instancias de proveedores de datos específicos (SQL o NoSQL)
 * utilizando el patrón de fábrica. Utiliza {@link EntityManager} para operaciones SQL y {@link MongoTemplate}
 * para operaciones NoSQL con Azure Cosmos DB.
 * </p>
 *
 * @Component indica a Spring que esta clase es un componente gestionado por el contenedor de Spring.
 */
@Component
@RequiredArgsConstructor
public class DataProviderFactory {

    private final EntityManager entityManager;
    private final MongoTemplate mongoTemplate;
    private final ILoggerService iLoggerService;

    /**
     * Obtiene una instancia de {@link IDataProvider} basada en el tipo de base de datos especificado.
     * <p>
     * Este método utiliza el patrón de fábrica para retornar la implementación adecuada de {@link IDataProvider}
     * (ya sea {@link SqlDataProvider} o {@link NoSqlDataProvider}) según el tipo de base de datos indicado.
     * </p>
     *
     * @param <T>         el tipo de la entidad
     * @param <ID>        el tipo del identificador de la entidad, que debe ser {@link Serializable}
     * @param entityClass la clase de la entidad para la cual se requiere el proveedor de datos
     * @param dbType      el tipo de base de datos a utilizar ("sql" o "nosql")
     * @return una instancia de {@link IDataProvider} correspondiente al tipo de base de datos
     * @throws IllegalArgumentException si el tipo de base de datos no es soportado
     */

    public <T, ID extends Serializable> IDataProvider<T, ID> getDataProvider(Class<T> entityClass, String dbType) {
        return switch (dbType) {
            case UTILITY_TYPE_REPOSITORY_SQL -> new SqlDataProvider<>(entityManager, entityClass,iLoggerService);
            case UTILITY_TYPE_REPOSITORY_NO_SQL -> new NoSqlDataProvider<>(mongoTemplate, entityClass,iLoggerService);
            default ->
                    throw new IllegalArgumentException(String.format(UTILITY_STRING_FORMAT, TYPE_NOT_SUPPORTED, dbType));
        };
    }
}
