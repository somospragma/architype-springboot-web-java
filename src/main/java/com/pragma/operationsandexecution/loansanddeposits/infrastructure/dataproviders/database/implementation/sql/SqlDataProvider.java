package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.sql;

import com.pragma.operationsandexecution.crosscutting.exceptions.ApiException;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerService;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.sql.ISqlDataProvider;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.*;

/**
 * Proveedor de datos para operaciones SQL utilizando JPA.
 * <p>
 * Esta clase implementa {@link ISqlDataProvider} y maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre entidades utilizando {@link EntityManager} de JPA.
 * </p>
 *
 * @param <T>  el tipo de la entidad
 * @param <ID> el tipo del identificador de la entidad, que debe ser {@link Serializable}
 */
@RequiredArgsConstructor
public class SqlDataProvider<T, ID extends Serializable> implements ISqlDataProvider<T, ID> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;
    private final ILoggerService loggerService;

    /**
     * Guarda una entidad en la base de datos.
     * <p>
     * Si la entidad no está gestionada por el {@link EntityManager}, se persiste; de lo contrario, se fusiona.
     * </p>
     *
     * @param <S>    el tipo de la entidad que extiende de {@code T}
     * @param entity la entidad a guardar
     * @return la entidad guardada
     * @throws RuntimeException si ocurre un error al guardar la entidad
     */
    @Override
    public <S extends T> S save(S entity) {
        try {
            if (!entityManager.contains(entity)) {
                entityManager.persist(entity);
                loggerService.logInfo(entity.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_SAVE,
                        ENTITY_PERSISTED_SUCCESSFULLY, entity.toString());
            } else {
                entityManager.merge(entity);
                loggerService.logInfo(entity.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_SAVE,
                        ENTITY_MERGED_SUCCESSFULLY, entity.toString());
            }
            return entity;
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_SAVE,
                    SQL_SAVE_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_SQL_SAVE_ERROR);
        }
    }

    /**
     * Elimina una entidad específica de la base de datos.
     * <p>
     * Si la entidad está gestionada por el {@link EntityManager}, se elimina directamente;
     * de lo contrario, se fusiona primero para gestionarla y luego se elimina.
     * </p>
     *
     * @param entity la entidad a eliminar
     */
    @Override
    public void delete(T entity) {
        try {
            T managedEntity = entityManager.contains(entity) ? entity : entityManager.merge(entity);
            entityManager.remove(managedEntity);
            loggerService.logInfo(managedEntity.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_DELETE,
                    ENTITY_DELETED_SUCCESSFULLY_SQL, managedEntity.toString());
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_DELETE,
                    SQL_ENTITY_DELETED_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_SQL_DELETE_ERROR);
        }
    }

    /**
     * Encuentra una entidad por su identificador.
     *
     * @param id el identificador de la entidad a encontrar
     * @return un {@link Optional} que contiene la entidad si se encuentra, o vacío si no existe
     */
    @Override
    public Optional<T> findById(ID id) {
        try {
            T entity = entityManager.find(entityClass, id);
            loggerService.logInfo(id.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_FIND_BY_ID,
                    ENTITY_FIND_BY_ID_SQL, "");
            return Optional.ofNullable(entity);
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_FIND_BY_ID,
                    SQL_FIND_BY_ID_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_SQL_FIND_BY_ID_ERROR);
        }
    }

    /**
     * Encuentra todas las entidades de este tipo en la base de datos.
     *
     * @return un {@link Iterable} que contiene todas las entidades encontradas
     */
    @SuppressWarnings(BEAN_SUPPRESS_WARNINGS)
    @Override
    public Iterable<T> findAll() {
        try {
            String query = UTILITY_SQL_QUERY_FIND_ALL_SELECT + entityClass.getSimpleName() + UTILITY_SQL_QUERY_FIND_ALL_VALUE;
            List<T> results = (List<T>) entityManager.createQuery(query).getResultList();
            loggerService.logInfo(entityClass.getSimpleName(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_FIND_ALL,
                    ALL_ENTITY_RETRIEVED, results);
            return results;
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_FIND_ALL,
                    SQL_FIND_ALL_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_SQL_FIND_ALL_ERROR);
        }
    }

    /**
     * Elimina una entidad de la base de datos por su identificador.
     *
     * @param id el identificador de la entidad a eliminar
     */
    @Override
    public void deleteById(ID id) {
        try {
            findById(id).ifPresent(this::delete);
            loggerService.logInfo(id.toString(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_DELETE_BY_ID,
                    ENTITY_DELETE_BY_ID_SQL, "");
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_DELETE_BY_ID,
                    SQL_DELETE_BY_ID_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_DATABASE_SQL_DELETE_BY_ID_ERROR);
        }
    }
}
