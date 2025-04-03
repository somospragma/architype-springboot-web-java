package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.nosql;

import com.pragma.operationsandexecution.crosscutting.exceptions.ApiException;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerService;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.nosql.INoSqlDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.*;

/**
 * Proveedor de datos para operaciones NoSQL utilizando Azure Cosmos DB.
 * <p>
 * Esta clase implementa {@link INoSqlDataProvider} y maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre entidades utilizando {@link MongoTemplate} de Spring Data para interactuar con Azure Cosmos DB.
 * </p>
 *
 * @param <T>  el tipo de la entidad
 * @param <ID> el tipo del identificador de la entidad, que debe ser {@link Serializable}
 */
@RequiredArgsConstructor
public class NoSqlDataProvider<T, ID extends Serializable> implements INoSqlDataProvider<T, ID> {

    private final MongoTemplate mongoTemplate;
    private final Class<T> entityClass;
    private final ILoggerService loggerService;

    /**
     * Guarda una entidad en la base de datos NoSQL.
     * <p>
     * Utiliza el método {@code upsert} de {@link MongoTemplate} para insertar o actualizar la entidad.
     * </p>
     *
     * @param <S>    el tipo de la entidad que extiende de {@code T}
     * @param entity la entidad a guardar
     * @return la entidad guardada
     */
    @Override
    public <S extends T> S save(S entity) {
        try {
            S entitySaved = mongoTemplate.save(entity);
            loggerService.logInfo(entitySaved.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_SAVE,
                    ENTITY_SAVED_NO_SQL, entitySaved.toString());
            return entitySaved;
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_SAVE,
                    NO_SQL_SAVE_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_NO_SQL_SAVE_ERROR);
        }
    }

    /**
     * Elimina una entidad específica de la base de datos NoSQL.
     * <p>
     * Utiliza el método {@code deleteEntity} de {@link MongoTemplate} para eliminar la entidad.
     * </p>
     *
     * @param entity la entidad a eliminar
     */
    @Override
    public void delete(T entity) {
        try {
            mongoTemplate.remove(entity);
            loggerService.logInfo(entity.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_DELETE,
                    ENTITY_DELETED_SUCCESSFULLY_NO_SQL, entity.toString());
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_DELETE,
                    NO_SQL_ENTITY_DELETED_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_NO_SQL_DELETE_ERROR);
        }
    }

    /**
     * Encuentra una entidad por su identificador en la base de datos NoSQL.
     *
     * @param id el identificador de la entidad a encontrar
     * @return un {@link Optional} que contiene la entidad si se encuentra, o vacío si no existe
     */
    @Override
    public Optional<T> findById(ID id) {
        try {
            T entity = mongoTemplate.findById(id, entityClass);
            loggerService.logInfo(id.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_FIND_BY_ID,
                    ENTITY_FIND_BY_ID_NO_SQL, entity);
            return Optional.ofNullable(entity);
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_FIND_BY_ID,
                    NO_SQL_FIND_BY_ID_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_NO_SQL_FIND_BY_ID_ERROR);
        }
    }

    /**
     * Encuentra todas las entidades de este tipo en la base de datos NoSQL.
     *
     * @return un {@link Iterable} que contiene todas las entidades encontradas
     */
    @Override
    public Iterable<T> findAll() {
        try {
            List<T> allEntity = mongoTemplate.findAll(entityClass);
            loggerService.logInfo(entityClass.getSimpleName(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_FIND_ALL,
                    ENTITY_FIND_ALL_NO_SQL, allEntity.toString());
            return allEntity;
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_FIND_ALL,
                    NO_SQL_FIND_ALL_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_NO_SQL_FIND_ALL_ERROR);
        }
    }

    /**
     * Elimina una entidad de la base de datos NoSQL por su identificador.
     *
     * @param id el identificador de la entidad a eliminar
     */
    @Override
    public void deleteById(ID id) {
        try {
            Query query = new Query(Criteria.where(UTILITY_CRITERIA_WHERE_MONGO).is(id));
            mongoTemplate.remove(query, entityClass);
            loggerService.logInfo(id.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_DELETE_BY_ID,
                    ENTITY_DELETE_BY_ID_NO_SQL, "");
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_DELETE_BY_ID,
                    NO_SQL_DELETE_BY_ID_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_NO_SQL_DELETE_BY_ID_ERROR);
        }
    }
}
