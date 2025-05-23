package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.messages.MessageType;

/**
 * Interfaz para el servicio de logging.
 * <p>
 * Define métodos para registrar mensajes de diferentes niveles.
 * </p>
 */
public interface ILoggerPort {

    /**
     * Registra un mensaje de información.
     *
     * @param message el contenido del mensaje de información
     */
    void logInfo(String transactionId, String layer, String message, Object additionalData);

    /**
     * Registra un mensaje de advertencia.
     *
     * @param message el contenido del mensaje de advertencia
     */
    void logWarning(String transactionId, String layer, String message, Object additionalData);

    /**
     * Registra un mensaje de error.
     *
     * @param message el contenido del mensaje de error
     */
    void logError(String transactionId, String layer, String message, Object additionalData);

    /**
     * Registra un mensaje de éxito.
     *
     * @param message el contenido del mensaje de éxito
     */
    void logSuccess(String transactionId, String layer, String message, Object additionalData);

    /**
     * Registra un mensaje personalizado según el tipo especificado.
     *
     * @param type    el tipo de mensaje
     * @param message el contenido del mensaje
     */
    void logMessage(MessageType type, String message);
}
