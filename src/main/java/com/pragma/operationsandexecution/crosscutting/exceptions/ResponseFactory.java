package com.pragma.operationsandexecution.crosscutting.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Fábrica para crear respuestas de forma consistente.
 * Se utiliza para crear tanto respuestas exitosas como respuestas de error.
 */
@Component
@RequiredArgsConstructor
public class ResponseFactory {

    /**
     * Crea una respuesta exitosa a partir de un HttpStatus, mensaje, datos y un transactionId.
     *
     * @param status        Estado HTTP de la respuesta (ej: HttpStatus.OK)
     * @param message       Mensaje de éxito.
     * @param data          Datos resultantes del proceso.
     * @param transactionId Identificador para trazabilidad.
     * @param <T>           Tipo de los datos.
     * @return ApiResponseDto con la estructura estándar de éxito.
     */
    public <T> ApiResponseDto<T> createSuccessResponse(HttpStatus status, String message, T data, String transactionId) {
        return new ApiResponseDto<>(String.valueOf(status.value()), message, data, transactionId);
    }

    /**
     * Crea una respuesta de error a partir de un estado HTTP, un mensaje, un transactionId y detalles del error.
     *
     * @param status        Estado HTTP del error.
     * @param message       Mensaje de error.
     * @param transactionId Identificador de trazabilidad.
     * @param details       Detalles del error (código, campos).
     * @return ResponseEntity con ApiErrorResponse.
     */
    public ResponseEntity<ApiErrorResponse> createErrorResponse(HttpStatus status, String message, String transactionId, ApiErrorResponse.ErrorDetails details) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                String.valueOf(status.value()),
                message,
                transactionId,
                details
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
