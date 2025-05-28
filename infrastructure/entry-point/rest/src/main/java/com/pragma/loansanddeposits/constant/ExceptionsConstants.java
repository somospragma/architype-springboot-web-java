package com.pragma.loansanddeposits.constant;

public class ExceptionsConstants {

    private ExceptionsConstants() {}

    /* Constantes Extras de utilidad */
    public static final String UTILITY_500_ERROR = "ERROR-500";
    public static final String UTILITY_EXCEPTION_TYPE = "exceptionType";
    public static final String UTILITY_EXCEPTION_MESSAGE = "exceptionMessage";

    /*  Constantes de errores */
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error - Ocurrió un error inesperado en el servidor";
    public static final String EMPTY_MESSAGE_INTERNAL_ERROR = "Sin mensaje interno";
    public static final String NOT_FOUND_ERROR = "No se encontró el error especificado.";
    public static final String NOT_AVAILABLE_TRANSACTION_ID_ERROR = "No esta disponible el transactionId.";

    /* Constantes de layers */
    public static final String LAYER_CROSSCUTTING_GLOBAL_EXCEPTION_HANDLER_RESOLVE_MESSAGE = "crosscutting.exceptions.GlobalExceptionHandler.resolveMessageSafely";
    public static final String LAYER_CROSSCUTTING_GLOBAL_EXCEPTION_HANDLER_GET_TRANSACTION_ID = "crosscutting.exceptions.GlobalExceptionHandler.getTransactionIdSafely";

    /* Constantes Extras de messageKey */
    public static final String MESSAGE_KEY_NOT_FOUND_ERROR = "exception.global.not.found.error";
    public static final String MESSAGE_KEY_NOT_AVAILABLE_TRANSACTION_ID_ERROR = "exception.global.notAvailable.transactionId.error";

    /* Constantes en excepciones */
    public static final String NO_RESOURCE_FOUND_EXCEPTION = "Formato incorrecto en la url.";
    public static final String MISSING_HEADER_EXCEPTION = "Falta el encabezado requerido en la solicitud";
    public static final String MISSING_PARAMETER_EXCEPTION = "Falta el encabezado requerido en la solicitud";
    public static final String GENERAL_EXCEPTION = "Ocurrió un error inesperado en el servidor.";

}
