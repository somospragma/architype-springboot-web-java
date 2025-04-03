package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.sql;

import java.io.Serializable;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic.IDataProvider;
import org.springframework.stereotype.Repository;

/**
 * Proveedor de datos específico para operaciones SQL.
 * <p>
 * Esta interfaz extiende de {@link IDataProvider} y define operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * específicas para bases de datos SQL. Se utiliza para interactuar con sistemas de persistencia relacional.
 * </p>
 *
 * @param <T>  el tipo de la entidad
 * @param <ID> el tipo del identificador de la entidad, que debe ser {@link Serializable}
 */
@Repository
public interface ISqlDataProvider<T, ID extends Serializable> extends IDataProvider<T, ID> {
}
