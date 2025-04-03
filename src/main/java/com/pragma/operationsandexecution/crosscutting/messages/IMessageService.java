package com.pragma.operationsandexecution.crosscutting.messages;

import org.springframework.web.context.request.WebRequest;

import java.util.Locale;

/**
 * Servicio para la obtención de mensajes internacionalizados
 * basados en una llave de mensaje.
 */
public interface IMessageService {

    /**
     * Obtiene el mensaje localizado dado una key y un Locale específico.
     *
     * @param key    Llave del mensaje
     * @param locale Locale para la localización
     * @return Mensaje localizado
     */
    String getMessage(String key, Locale locale);

    /**
     * Obtiene el mensaje localizado dado una key usando el Locale por defecto.
     *
     * @param key Llave del mensaje
     * @return Mensaje localizado
     */
    String getMessage(String key);

    /**
     * Obtiene el transactionId desde el request.
     * Si no existe, genera uno por defecto.
     *
     * @param request WebRequest actual.
     * @return transactionId.
     */
    String getTransactionId(WebRequest request);
}
