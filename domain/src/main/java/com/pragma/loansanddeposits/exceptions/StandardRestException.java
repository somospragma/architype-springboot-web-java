package com.pragma.loansanddeposits.exceptions;

import lombok.Getter;

@Getter
public class StandardRestException extends RuntimeException {

    private final Integer statusCode;
    private final Object errorResponseBody;
    public static final String EXTERNAL_EXCEPTION = "Ocurrió un error en la llamada a servicio externo";

    /**
     * Constructor principal para StandardRestException.
     */
    public StandardRestException(Integer statusCode, Object errorResponseBody) {
        super(EXTERNAL_EXCEPTION);
        this.statusCode = statusCode;
        this.errorResponseBody = errorResponseBody;
    }
}
