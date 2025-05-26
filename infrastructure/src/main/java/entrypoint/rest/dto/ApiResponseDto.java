package entrypoint.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * DTO para respuestas exitosas.
 *
 * @param <T> Tipo del objeto data que se retornará en la respuesta.
 */
@Getter
@Setter
public class ApiResponseDto<T> {
    private String statusCode;
    private String status;
    private String message;
    private T data;
    private ZonedDateTime timestamp;
    private String transactionId;

    /**
     * Crea una respuesta exitosa.
     *
     * @param statusCode Código de estado HTTP en formato String (ej: "200").
     * @param message    Mensaje descriptivo del resultado.
     * @param data       Datos resultantes del proceso.
     * @param transactionId    Identificador único del request, útil para trazabilidad.
     */
    public ApiResponseDto(String statusCode, String message, T data, String transactionId) {
        this.statusCode = statusCode;
        this.status = "SUCCESS";
        this.message = message;
        this.data = data;
        this.timestamp = ZonedDateTime.now();
        this.transactionId = transactionId;
    }
}
