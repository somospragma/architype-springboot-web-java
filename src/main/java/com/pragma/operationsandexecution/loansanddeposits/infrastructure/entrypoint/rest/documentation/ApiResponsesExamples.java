package com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.documentation;

public class ApiResponsesExamples {

    // 200 OK - Ejemplo de respuesta exitosa con datos
    public static final String SUCCESS_200_EXAMPLE = "{\n"
            + "  \"statusCode\": \"200\",\n"
            + "  \"status\": \"SUCCESS\",\n"
            + "  \"message\": \"Recurso Creado Exitosamente.\",\n"
            + "  \"data\": {\n"
            + "    \"loanId\": \"1045878\",\n"
            + "    \"amount\": 4578.00,\n"
            + "    \"interestRate\": 3.5,\n"
            + "    \"startDate\": \"2024-01-01\",\n"
            + "    \"endDate\": \"2024-12-31\"\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    // 204 No Content - Procesado correctamente sin contenido
    // Aquí retornamos un body con un mensaje descriptivo. Aunque 204 indica que no debe haber body,
    // para propósito de ejemplo documental, lo dejamos. Si se requiere totalmente vacío, no incluyas data.
    public static final String SUCCESS_204_EXAMPLE = "{\n"
            + "  \"statusCode\": \"204\",\n"
            + "  \"status\": \"SUCCESS\",\n"
            + "  \"message\": \"Solicitud procesada correctamente, sin contenido a retornar.\",\n"
            + "  \"data\": null,\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    // 400 Bad Request
    public static final String ERROR_400_EXAMPLE = "{\n"
            + "  \"statusCode\": \"400\",\n"
            + "  \"status\": \"ERROR\",\n"
            + "  \"message\": \"La Solicitud Falló en una de las Validaciones.\",\n"
            + "  \"data\": {\n"
            + "    \"errorDetails\": {\n"
            + "      \"code\": \"ERROR-400\",\n"
            + "      \"fields\": {\n"
            + "        \"loanId\": \"loanId es requerido.\",\n"
            + "        \"email\": \"Formato inválido para el Campo loanId.\"\n"
            + "      }\n"
            + "    }\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    // 401 Unauthorized
    public static final String ERROR_401_EXAMPLE = "{\n"
            + "  \"statusCode\": \"401\",\n"
            + "  \"status\": \"ERROR\",\n"
            + "  \"message\": \"El usuario no está autenticado.\",\n"
            + "  \"data\": {\n"
            + "    \"errorDetails\": {\n"
            + "      \"code\": \"ERROR-401\",\n"
            + "      \"fields\": null\n"
            + "    }\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    // 403 Forbidden
    public static final String ERROR_403_EXAMPLE = "{\n"
            + "  \"statusCode\": \"403\",\n"
            + "  \"status\": \"ERROR\",\n"
            + "  \"message\": \"El usuario no tiene permisos para ejecutar el API.\",\n"
            + "  \"data\": {\n"
            + "    \"errorDetails\": {\n"
            + "      \"code\": \"ERROR-403\",\n"
            + "      \"fields\": null\n"
            + "    }\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    // 404 Not Found
    public static final String ERROR_404_EXAMPLE = "{\n"
            + "  \"statusCode\": \"404\",\n"
            + "  \"status\": \"ERROR\",\n"
            + "  \"message\": \"Recurso no encontrado.\",\n"
            + "  \"data\": {\n"
            + "    \"errorDetails\": {\n"
            + "      \"code\": \"ERROR-404\",\n"
            + "      \"fields\": null\n"
            + "    }\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    // 408 Request Timeout
    public static final String ERROR_408_EXAMPLE = "{\n"
            + "  \"statusCode\": \"408\",\n"
            + "  \"status\": \"ERROR\",\n"
            + "  \"message\": \"La solicitud excedió el tiempo límite establecido.\",\n"
            + "  \"data\": {\n"
            + "    \"errorDetails\": {\n"
            + "      \"code\": \"ERROR-408\",\n"
            + "      \"fields\": null\n"
            + "    }\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    // 409 Conflict
    public static final String ERROR_409_EXAMPLE = "{\n"
            + "  \"statusCode\": \"409\",\n"
            + "  \"status\": \"ERROR\",\n"
            + "  \"message\": \"Hay un conflicto con el estado actual del recurso.\",\n"
            + "  \"data\": {\n"
            + "    \"errorDetails\": {\n"
            + "      \"code\": \"ERROR-409\",\n"
            + "      \"fields\": null\n"
            + "    }\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    // 500 Internal Server Error
    public static final String ERROR_500_EXAMPLE = "{\n"
            + "  \"statusCode\": \"500\",\n"
            + "  \"status\": \"ERROR\",\n"
            + "  \"message\": \"Ocurrió un error inesperado en el servidor.\",\n"
            + "  \"data\": {\n"
            + "    \"errorDetails\": {\n"
            + "      \"code\": \"ERROR-500\",\n"
            + "      \"fields\": null\n"
            + "    }\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-12-11T16:34:56.6216008-05:00\",\n"
            + "  \"transactionId\": \"generated-fallback-transactionId\"\n"
            + "}";

    // 501 Not Implemented
    public static final String ERROR_501_EXAMPLE = "{\n"
            + "  \"statusCode\": \"501\",\n"
            + "  \"status\": \"ERROR\",\n"
            + "  \"message\": \"El servidor no pudo completar la solicitud en el tiempo permitido.\",\n"
            + "  \"data\": {\n"
            + "    \"errorDetails\": {\n"
            + "      \"code\": \"ERROR-501\",\n"
            + "      \"fields\": null\n"
            + "    }\n"
            + "  },\n"
            + "  \"timestamp\": \"2024-11-29T12:34:56Z\",\n"
            + "  \"transactionId\": \"abcd-1234-efgh-5678\"\n"
            + "}";

    /*public static final String SUCCESS_200_EXAMPLE = JsonResponseTemplates.buildSuccessExample(
            "200", "SUCCESS", "Recurso Creado Exitosamente.",
            "1045878", 4578.00, 3.5,
            "2024-01-01", "2024-12-31",
            "2024-11-29T12:34:56Z", "abcd-1234-efgh-5678"
    );

    public static final String ERROR_400_EXAMPLE = JsonResponseTemplates.buildErrorExample(
            "400", "ERROR", "La Solicitud Falló en una de las Validaciones.",
            "ERROR-400",
            "{\n  \"loanId\": \"loanId es requerido.\",\n  \"email\": \"Formato inválido para el Campo loanId.\" \n}",
            "2024-11-29T12:34:56Z", "abcd-1234-efgh-5678"
    );

    public static final String ERROR_404_EXAMPLE = JsonResponseTemplates.buildErrorExample(
            "404", "ERROR", "Recurso no encontrado.",
            "ERROR-404",
            null,
            "2024-11-29T12:34:56Z", "abcd-1234-efgh-5678"
    );

    public static final String ERROR_500_EXAMPLE = JsonResponseTemplates.buildErrorExample(
            "500", "ERROR", "Ocurrió un error inesperado en el servidor.",
            "ERROR-500",
            null,
            "2024-12-11T16:34:56.6216008-05:00", "generated-fallback-transactionId"
    );*/

    // Aquí podrías agregar los demás estados HTTP (401, 403, 408, 409, 501) de forma similar.
}
