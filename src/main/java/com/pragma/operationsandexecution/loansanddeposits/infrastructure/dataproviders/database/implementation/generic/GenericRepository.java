package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.generic;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic.IDataProvider;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic.IGenericRepository;

import java.io.Serializable;
import java.util.Optional;

/**
 * Repositorio genérico para manejar operaciones CRUD sobre entidades.
 * <p>
 * Esta clase implementa la interfaz {@link IGenericRepository} y utiliza un {@link IDataProvider}
 * para delegar las operaciones de persistencia, permitiendo interactuar tanto con bases de datos SQL como NoSQL.
 * </p>
 *
 * @param <T>  el tipo de la entidad
 * @param <ID> el tipo del identificador de la entidad, que debe ser {@link Serializable}
 */
public class GenericRepository<T, ID extends Serializable> implements IGenericRepository<T, ID> {
    private final IDataProvider<T, ID> dataProvider;

    /**
     * Constructor para inyectar el proveedor de datos adecuado basado en el tipo de base de datos.
     *
     * @param entityClass         la clase de la entidad manejada por este repositorio
     * @param dataProviderFactory la fábrica para obtener el proveedor de datos correspondiente
     * @param dbType              el tipo de base de datos a utilizar ("sql" o "nosql")
     */
    public GenericRepository(Class<T> entityClass, DataProviderFactory dataProviderFactory, String dbType) {
        this.dataProvider = dataProviderFactory.getDataProvider(entityClass, dbType);

    }

    /**
     * Guarda una entidad en la base de datos.
     *
     * @param <S>    el tipo de la entidad que extiende de {@code T}
     * @param entity la entidad a guardar
     * @return la entidad guardada
     */
    @Override
    public <S extends T> S save(S entity) {
        return dataProvider.save(entity);
    }

    /**
     * Elimina una entidad de la base de datos por su identificador.
     *
     * @param id el identificador de la entidad a eliminar
     */
    @Override
    public void deleteById(ID id) {
        dataProvider.deleteById(id);
    }

    /**
     * Encuentra una entidad por su identificador.
     *
     * @param id el identificador de la entidad a encontrar
     * @return un {@link Optional} que contiene la entidad si se encuentra, o vacío si no existe
     */
    @Override
    public Optional<T> findById(ID id) {
        return dataProvider.findById(id);
    }

    /**
     * Encuentra todas las entidades de este tipo en la base de datos.
     *
     * @return un {@link Iterable} que contiene todas las entidades encontradas
     */
    @Override
    public Iterable<T> findAll() {
        return dataProvider.findAll();
    }

    /**
     * Elimina una entidad específica de la base de datos.
     *
     * @param entity la entidad a eliminar
     */
    @Override
    public void delete(T entity) {
        dataProvider.delete(entity);
    }
}
