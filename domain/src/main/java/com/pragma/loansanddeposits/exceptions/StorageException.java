package com.pragma.loansanddeposits.exceptions;

/**
 * Excepción personalizada para errores de almacenamiento.
 */
public class StorageException extends Exception {

    /**
     * Constructor con mensaje y causa.
     *
     * @param message el mensaje de la excepción
     * @param cause   la causa original de la excepción
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor con solo mensaje.
     *
     * @param message el mensaje de la excepción
     */
    public StorageException(String message) {
        super(message);
    }
}
