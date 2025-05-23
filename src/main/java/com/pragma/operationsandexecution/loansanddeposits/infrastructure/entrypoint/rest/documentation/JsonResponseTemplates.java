package com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.documentation;

public class JsonResponseTemplates {
    
    // Plantilla genérica para una respuesta de éxito con datos de préstamo
    // Note que usamos %s y %f como placeholders para inyectar valores dinámicos
    private static final String SUCCESS_TEMPLATE = """
    {
      "statusCode": "%s",
      "status": "%s",
      "message": "%s",
      "data": {
        "loanId": "%s",
        "amount": %f,
        "interestRate": %f,
        "startDate": "%s",
        "endDate": "%s"
      },
      "timestamp": "%s",
      "transactionId": "%s"
    }
    """;

    // Plantilla genérica para una respuesta de error
    // Nótese que errorDetails puede tener fields o ser null
    private static final String ERROR_TEMPLATE = """
    {
      "statusCode": "%s",
      "status": "%s",
      "message": "%s",
      "data": {
        "errorDetails": {
          "code": "%s",
          "fields": %s
        }
      },
      "timestamp": "%s",
      "transactionId": "%s"
    }
    """;

    /**
     * Construye un JSON de ejemplo para una respuesta exitosa inyectando variables.
     *
     * @param statusCode Código de estado HTTP en string (ej: "200")
     * @param status Texto del estado (ej: "SUCCESS")
     * @param message Mensaje descriptivo (ej: "Recurso Creado Exitosamente.")
     * @param loanId Id del préstamo (ej: "1045878")
     * @param amount Monto del préstamo (ej: 4578.00)
     * @param interestRate Tasa de interés (ej: 3.5)
     * @param startDate Fecha de inicio (ej: "2024-01-01")
     * @param endDate Fecha fin (ej: "2024-12-31")
     * @param timestamp Marca de tiempo (ej: "2024-11-29T12:34:56Z")
     * @param transactionId Identificador de trazabilidad (ej: "abcd-1234-efgh-5678")
     * @return String con el JSON formateado.
     */
    public static String buildSuccessExample(String statusCode, String status, String message,
                                             String loanId, double amount, double interestRate,
                                             String startDate, String endDate,
                                             String timestamp, String transactionId) {
        return String.format(SUCCESS_TEMPLATE, statusCode, status, message, loanId, amount, interestRate, startDate, endDate, timestamp, transactionId);
    }

    /**
     * Construye un JSON de ejemplo para una respuesta de error inyectando variables.
     *
     * @param statusCode Código de estado HTTP (ej: "400", "500")
     * @param status Texto del estado (ej: "ERROR")
     * @param message Mensaje descriptivo del error
     * @param errorCode Código interno del error (ej: "ERROR-500")
     * @param fields JSON con los campos y sus errores, puede ser null o un objeto JSON con llaves y valores.
     *               Por ejemplo: null o "{\n  \"loanId\": \"loanId es requerido.\"\n}"
     * @param timestamp Marca de tiempo
     * @param transactionId Identificador de trazabilidad
     * @return String con el JSON del error formateado.
     */
    public static String buildErrorExample(String statusCode, String status, String message,
                                           String errorCode, String fields,
                                           String timestamp, String transactionId) {
        // Si fields es null, lo representamos como null en el JSON
        String fieldsValue = (fields == null) ? "null" : fields;
        return String.format(ERROR_TEMPLATE, statusCode, status, message, errorCode, fieldsValue, timestamp, transactionId);
    }
}
