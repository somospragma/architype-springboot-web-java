package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic;

import java.util.Optional;

/**
 * Repositorio genérico para manejar operaciones CRUD sobre entidades.
 * <p>
 * Esta interfaz define métodos estándar para crear, leer, actualizar y eliminar entidades
 * de cualquier tipo {@code T} con identificadores de tipo {@code ID}.
 * </p>
 *
 * @param <T>  el tipo de la entidad
 * @param <ID> el tipo del identificador de la entidad
 */
public interface IGenericRepository<T, ID>  {

    /**
     * Guarda una entidad en la base de datos.
     *
     * @param <S>    el tipo de la entidad que extiende de {@code T}
     * @param entity la entidad a guardar
     * @return la entidad guardada
     */
    <S extends T> S save(S entity);

    /**
     * Elimina una entidad específica de la base de datos.
     *
     * @param entity la entidad a eliminar
     */
    void delete(T entity);

    /**
     * Encuentra una entidad por su identificador.
     *
     * @param id el identificador de la entidad a encontrar
     * @return un {@link Optional} que contiene la entidad si se encuentra, o vacío si no existe
     */
    Optional<T> findById(ID id);

    /**
     * Encuentra todas las entidades de este tipo en la base de datos.
     *
     * @return un {@link Iterable} que contiene todas las entidades encontradas
     */
    Iterable<T> findAll();

    /**
     * Elimina una entidad de la base de datos por su identificador.
     *
     * @param id el identificador de la entidad a eliminar
     */
    void deleteById(ID id);
}

