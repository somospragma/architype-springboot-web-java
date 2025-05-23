package com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants;

public class SwaggerConstants {

    /* Constants commonApiResponse */
    public static final String SWAGGER_API_RESPONSE_COMMON_API_200 = "OK - Recurso creado exitosamente";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_204 = "No Content - Procesado correctamente sin contenid";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_400 = "Bad Request - Datos inválidos o formato incorrecto";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_401 = "Unauthorized - El usuario no está autenticado";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_403 = "Forbidden - El usuario no tiene permisos para ejecutar el API";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_404 = "Not Found - Recurso no encontrado";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_408 = "Request Timeout - La solicitud excedió el tiempo límite establecido";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_409 = "Conflict - Hay un conflicto con el estado actual del recurso";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_500 = "Internal Server Error - Ocurrió un error inesperado en el servidor";
    public static final String SWAGGER_API_RESPONSE_COMMON_API_501 = "Not Implemented - El servidor no pudo completar la solicitud en el tiempo permitido";

    /* Constants createLoan */
    public static final String SWAGGER_OPERATION_CREATE_LOGAN = "Crear un nuevo préstamo";

    /* Constants getLoanById */
    public static final String SWAGGER_OPERATION_GET_LOAN_BY_ID = "Obtener un préstamo por su ID";

    /* Constantes DTO*/
    public static final String SWAGGER_SCHEMA_LOAN_ID_DESCRIPTION = "Identificador único del préstamo";
    public static final String SWAGGER_SCHEMA_LOAN_ID_EXAMPLE = "12345";
    public static final String SWAGGER_SCHEMA_AMOUNT_DESCRIPTION = "Cantidad del préstamo";
    public static final String SWAGGER_SCHEMA_AMOUNT_EXAMPLE = "10000.00";
    public static final String SWAGGER_SCHEMA_INTEREST_RATE_DESCRIPTION = "Tasa de interés del préstamo";
    public static final String SWAGGER_SCHEMA_INTEREST_RATE_EXAMPLE = "5.5";
    public static final String SWAGGER_SCHEMA_START_DATE_DESCRIPTION = "Fecha de inicio del préstamo";
    public static final String SWAGGER_SCHEMA_START_DATE_EXAMPLE = "2024-01-01";
    public static final String SWAGGER_SCHEMA_END_DATE_DESCRIPTION = "Fecha de finalización del préstamo";
    public static final String SWAGGER_SCHEMA_END_DATE_EXAMPLE = "2024-12-31";
    public static final String SWAGGER_SCHEMA_LOAN_DTO = "DTO para la creación de un préstamo";

    /* Constantes Extras de utilidad */
    public static final String UTILITY_OPEN_API_GROUP = "Pragma Arquetipo - Arquitectura Hexagonal";
    public static final String UTILITY_OPEN_API_PATHS_TO_MATCH = "/**";
    public static final String UTILITY_OPEN_API_INFO_TITLE = "Java 21 + Springboot 3.3.5 & OpenAPI";
    public static final String UTILITY_OPEN_API_INFO_DESCRIPTION = "Este es un Arquetipo para todos los Microservicios de Pragma";
    public static final String UTILITY_OPEN_API_INFO_VERSION = "3.0.0";
    public static final String UTILITY_OPEN_API_INFO_CONTACT_NAME = "Pragma";
    public static final String UTILITY_OPEN_API_INFO_CONTACT_URL = "https://github.com/somospragma";
    public static final String UTILITY_OPEN_API_INFO_CONTACT_EMAIL = "jairo.duarte@pragma.com.co";
    public static final String UTILITY_OPEN_API_COMPONENTS_INFO_TITLE = "Contact Application API";
    public static final String UTILITY_OPEN_API_COMPONENTS_INFO_DESCRIPTION = "Este es un Arquetipo para todos los Microservicios de Pragma";

}
