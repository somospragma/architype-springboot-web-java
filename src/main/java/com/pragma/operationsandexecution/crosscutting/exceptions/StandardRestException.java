package com.pragma.operationsandexecution.crosscutting.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class StandardRestException extends RuntimeException {

    private final HttpStatusCode statusCode;
    private final Object errorResponseBody;
    public static final String EXTERNAL_EXCEPTION = "Ocurrió un error en la llamada a servicio externo";

    /**
     * Constructor principal para StandardRestException.
     */
    public <T> StandardRestException(HttpStatusCode statusCode, T errorResponseBody) {
        super(EXTERNAL_EXCEPTION);
        this.statusCode = statusCode;
        this.errorResponseBody = errorResponseBody;
    }

    public HttpStatus getStatusCodeAsHttpStatus() {
        return HttpStatus.valueOf(statusCode.value());
    }

}
