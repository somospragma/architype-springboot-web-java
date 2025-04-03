package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.nosql;

import java.io.Serializable;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic.IDataProvider;
import org.springframework.stereotype.Repository;

/**
 * Proveedor de datos específico para operaciones NoSQL.
 * <p>
 * Esta interfaz extiende de {@link IDataProvider} y define operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * específicas para bases de datos NoSQL. Se utiliza para interactuar con sistemas de persistencia NoSQL como Azure Cosmos DB.
 * </p>
 *
 * @param <T>  el tipo de la entidad
 * @param <ID> el tipo del identificador de la entidad, que debe ser {@link Serializable}
 */
@Repository
public interface INoSqlDataProvider<T, ID extends Serializable> extends IDataProvider<T, ID> {
}