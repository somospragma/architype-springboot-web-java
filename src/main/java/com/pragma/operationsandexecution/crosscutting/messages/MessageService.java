package com.pragma.operationsandexecution.crosscutting.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

/**
 * Implementación del servicio de mensajes que obtiene los textos desde un MessageSource.
 */

@RequiredArgsConstructor
@Service
public class MessageService implements IMessageService {

    /**
     * Fuente de mensajes inyectada por Spring para internacionalización.
     */
    private final MessageSource messageSource;

    /**
     * Obtiene el mensaje localizado dado una key y un Locale específico.
     *
     * @param key    Llave del mensaje
     * @param locale Locale para la localización
     * @return Mensaje localizado
     */
    @Override
    public String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * Obtiene el mensaje localizado dado una key usando el Locale por defecto.
     *
     * @param key Llave del mensaje
     * @return Mensaje localizado
     */
    @Override
    public String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    /**
     * Obtiene el transactionId desde el request.
     * Si no existe, genera uno por defecto.
     *
     * @param request WebRequest actual.
     * @return transactionId.
     */
    @Override
    public String getTransactionId(WebRequest request) {
        String transactionId = request.getHeader("transactionId");
        return (transactionId != null && !transactionId.isEmpty()) ? transactionId : "generated-fallback-transactionId";
    }
}
