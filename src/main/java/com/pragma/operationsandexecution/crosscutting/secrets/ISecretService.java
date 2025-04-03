package com.pragma.operationsandexecution.crosscutting.secrets;

import java.util.Optional;

/**
 * Interfaz para el servicio de manejo de secretos.
 * <p>
 * Define métodos para obtener secretos desde un almacén de secretos.
 * </p>
 */
public interface ISecretService {

    /**
     * Obtiene un secreto por su nombre.
     *
     * @param secretName el nombre del secreto a obtener
     * @return un {@link Optional} que contiene el valor del secreto si existe
     */
    String getSecret(String secretName);
}
