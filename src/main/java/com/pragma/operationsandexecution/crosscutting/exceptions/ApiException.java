package com.pragma.operationsandexecution.crosscutting.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * Excepción genérica para representar errores a nivel de API.
 * Incluye:
 * - Estado HTTP
 * - Código interno de error
 * - Llave de mensaje (messageKey) para obtener el mensaje del servicio de mensajes
 * - Campos con errores de validación (opcionales)
 */
@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String messageKey;
    private final Map<String, String> validationErrors;

    /**
     * Constructor principal para ApiException.
     *
     * @param httpStatus       Estado HTTP a retornar.
     * @param errorCode        Código interno del error.
     * @param messageKey       Llave para resolver el mensaje desde el servicio de mensajes.
     * @param validationErrors Mapa de campos con sus respectivos mensajes de error (opcional).
     */
    public ApiException(HttpStatus httpStatus, String errorCode, String messageKey, Map<String, String> validationErrors) {
        super(messageKey);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.messageKey = messageKey;
        this.validationErrors = validationErrors;
    }

    /**
     * Constructor sin mapa de errores.
     *
     * @param httpStatus Estado HTTP
     * @param errorCode  Código interno del error
     * @param messageKey Llave del mensaje
     */
    public ApiException(HttpStatus httpStatus, String errorCode, String messageKey) {
        this(httpStatus, errorCode, messageKey, null);
    }
}
