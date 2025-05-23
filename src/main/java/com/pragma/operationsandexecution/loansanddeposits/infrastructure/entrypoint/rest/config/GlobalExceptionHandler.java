package com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.config;

import com.pragma.operationsandexecution.loansanddeposits.domain.exceptions.ApiErrorResponse;
import com.pragma.operationsandexecution.loansanddeposits.domain.exceptions.ApiException;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.ResponseFactory;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerPort;
import com.pragma.operationsandexecution.loansanddeposits.application.usecase.MessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.EMPTY_MESSAGE_INTERNAL_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.INTERNAL_SERVER_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.LAYER_CROSSCUTTING_GLOBAL_EXCEPTION_HANDLER_GET_TRANSACTION_ID;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.LAYER_CROSSCUTTING_GLOBAL_EXCEPTION_HANDLER_RESOLVE_MESSAGE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.MESSAGE_KEY_NOT_AVAILABLE_TRANSACTION_ID_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.MESSAGE_KEY_NOT_FOUND_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.MISSING_HEADER_EXCEPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.MISSING_PARAMETER_EXCEPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.NOT_AVAILABLE_TRANSACTION_ID_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.NOT_FOUND_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.NO_RESOURCE_FOUND_EXCEPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.UTILITY_500_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.UTILITY_EXCEPTION_MESSAGE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.ExceptionsConstants.UTILITY_EXCEPTION_TYPE;

/**
 * Manejador global de excepciones para capturar y devolver respuestas homogéneas.
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    /**
     * Servicio para obtener mensajes internacionalizados.
     */
    private final MessageUseCase messageUseCase;
    /**
     * Fábrica para crear respuestas de error consistentes.
     */
    private final ResponseFactory responseFactory;
    /**
     * Servicio de logger para el ApplicationInsights
     */
    private final ILoggerPort loggerService;

    /**
     * Maneja las ApiException lanzadas desde el dominio/servicios.
     *
     * @param apiException Excepción lanzada.
     * @param request      Objeto request para obtener información del contexto.
     * @return Respuesta con detalle del error.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException apiException, WebRequest request) {
        String resolvedMessage = resolveMessageSafely(apiException.getMessageKey());
        ApiErrorResponse.ErrorDetails details = new ApiErrorResponse.ErrorDetails(
                apiException.getErrorCode(),
                apiException.getValidationErrors()
        );
        return responseFactory.createErrorResponse(
                apiException.getHttpStatus(),
                resolvedMessage,
                getTransactionIdSafely(request),
                details
        );
    }

    /**
     * Maneja excepciones genéricas no controladas.
     *
     * @param exception Excepción genérica.
     * @param request   Objeto request
     * @return Respuesta de error interno.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception exception, WebRequest request) {
        var details = new ApiErrorResponse.ErrorDetails(
                UTILITY_500_ERROR,
                Map.of(UTILITY_EXCEPTION_TYPE, exception.getClass().getSimpleName(), UTILITY_EXCEPTION_MESSAGE
                        , exception.getMessage() != null ? exception.getMessage() : EMPTY_MESSAGE_INTERNAL_ERROR
                )
        );
        return responseFactory.createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                INTERNAL_SERVER_ERROR,
                getTransactionIdSafely(request),
                details
        );
    }


    /**
     * Maneja la excepción cuando falta un header en la petición.
     *
     * @param ex Excepción lanzada cuando falta un header requerido.
     * @param request Objeto request para obtener información del contexto.
     * @return Respuesta con detalle del error y código 400.
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex, WebRequest request) {
        // Extraer el nombre del encabezado que falta
        String missingHeader = ex.getHeaderName();

        // Mensaje traducido
        String translatedMessage = MISSING_HEADER_EXCEPTION + " '" + missingHeader + "'.";

        Map<String, String> fields = Map.of(
                "exceptionType", ex.getClass().getSimpleName(),
                "exceptionMessage", translatedMessage
        );

        ApiErrorResponse.ErrorDetails details = new ApiErrorResponse.ErrorDetails("ERROR-400", fields);

        return responseFactory.createErrorResponse(
                HttpStatus.BAD_REQUEST,
                translatedMessage,
                messageUseCase.getTransactionId(request),
                details
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingRequestHeaderException(MissingServletRequestParameterException ex, WebRequest request) {
        // Extraer el nombre del encabezado que falta
        String missingParameter = ex.getParameterName();

        // Mensaje traducido
        String translatedMessage = MISSING_PARAMETER_EXCEPTION + " '" + missingParameter + "'.";

        Map<String, String> fields = Map.of(
                "exceptionType", ex.getClass().getSimpleName(),
                "exceptionMessage", translatedMessage
        );

        ApiErrorResponse.ErrorDetails details = new ApiErrorResponse.ErrorDetails("ERROR-400", fields);

        return responseFactory.createErrorResponse(
                HttpStatus.BAD_REQUEST,
                translatedMessage,
                messageUseCase.getTransactionId(request),
                details
        );
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleGenericNoResourceException(NoResourceFoundException ex, WebRequest request) {

        Map<String, String> fields = Map.of(
                "exceptionType", ex.getClass().getSimpleName(),
                "exceptionMessage", NO_RESOURCE_FOUND_EXCEPTION
        );

        ApiErrorResponse.ErrorDetails details = new ApiErrorResponse.ErrorDetails("ERROR-404", fields);
        return responseFactory.createErrorResponse(
                HttpStatus.NOT_FOUND,
                NO_RESOURCE_FOUND_EXCEPTION,
                messageUseCase.getTransactionId(request),
                details
        );
    }

    /**
     * Resuelve un mensaje de error utilizando el servicio de mensajes, garantizando seguridad frente a excepciones inesperadas.
     *
     * @param messageKey Clave del mensaje.
     * @return Mensaje resuelto o un valor por defecto.
     */
    private String resolveMessageSafely(String messageKey) {
        try {
            return messageUseCase.getMessage(messageKey);
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_CROSSCUTTING_GLOBAL_EXCEPTION_HANDLER_RESOLVE_MESSAGE,
                    NOT_FOUND_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_NOT_FOUND_ERROR);
        }
    }

    /**
     * Obtiene el transactionId del contexto del request de manera segura.
     *
     * @param request WebRequest.
     * @return transactionId si está disponible, o "Sin transactionId".
     */
    private String getTransactionIdSafely(WebRequest request) {
        try {
            return messageUseCase.getTransactionId(request);
        } catch (Exception exception) {
            loggerService.logError(exception.getLocalizedMessage(), LAYER_CROSSCUTTING_GLOBAL_EXCEPTION_HANDLER_GET_TRANSACTION_ID,
                    NOT_AVAILABLE_TRANSACTION_ID_ERROR, exception.getMessage());
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), MESSAGE_KEY_NOT_AVAILABLE_TRANSACTION_ID_ERROR);
        }
    }

}
