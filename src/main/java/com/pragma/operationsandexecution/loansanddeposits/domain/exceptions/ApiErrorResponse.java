package com.pragma.operationsandexecution.loansanddeposits.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Map;

/**
 * DTO para respuestas de error.
 * Contiene el estado ERROR, mensaje, timestamp, transactionId y detalles opcionales.
 */
@Getter
@Setter
public class ApiErrorResponse {

    private String statusCode;
    private String status;
    private String message;
    private ErrorData data;
    private ZonedDateTime timestamp;
    private String transactionId;

    /**
     * Construye una respuesta de error.
     *
     * @param statusCode   Código de estado HTTP en formato String (ej: "404").
     * @param message      Mensaje de error resuelto.
     * @param transactionId      Identificador de trazabilidad.
     * @param errorDetails Detalles del error (código interno, campos).
     */
    public ApiErrorResponse(String statusCode, String message, String transactionId, ErrorDetails errorDetails) {
        this.statusCode = statusCode;
        this.status = "ERROR";
        this.message = message;
        this.timestamp = ZonedDateTime.now();
        this.transactionId = transactionId;
        this.data = new ErrorData(errorDetails);
    }

    /**
     * Clase interna para representar la data en caso de error.
     * Contiene el objeto errorDetails.
     */
    @Getter
    @Setter
    public static class ErrorData {
        private ErrorDetails errorDetails;

        public ErrorData(ErrorDetails errorDetails) {
            this.errorDetails = errorDetails;
        }
    }

    /**
     * Clase interna para detallar el error.
     * Incluye un código y un mapeo de campos con errores.
     */
    @Getter
    @Setter
    public static class ErrorDetails {
        private String code;
        private Map<String, String> fields;

        /**
         * Construye los detalles del error.
         *
         * @param code   Código interno de error
         * @param fields Campos con sus mensajes de error
         */
        public ErrorDetails(String code, Map<String, String> fields) {
            this.code = code;
            this.fields = fields;
        }
    }
}
