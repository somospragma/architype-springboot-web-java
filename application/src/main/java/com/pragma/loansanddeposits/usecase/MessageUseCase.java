package com.pragma.loansanddeposits.usecase;

import com.pragma.loansanddeposits.port.out.IMessagePort;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

/**
 * Implementación del servicio de mensajes que obtiene los textos desde un MessageSource.
 */
@RequiredArgsConstructor
public class MessageUseCase {

    /**
     * Fuente de mensajes inyectada por Spring para internacionalización.
     */
    private final IMessagePort messagePort;

    /**
     * Obtiene el mensaje localizado dado una key y un Locale específico.
     *
     * @param key    Llave del mensaje
     * @param locale Locale para la localización
     * @return Mensaje localizado
     */
    public String getMessage(String key, Locale locale) {
        return messagePort.getMessage(key, locale);
    }

    /**
     * Obtiene el mensaje localizado dado una key usando el Locale por defecto.
     *
     * @param key Llave del mensaje
     * @return Mensaje localizado
     */
    public String getMessage(String key) {
        return messagePort.getMessage(key, Locale.getDefault());
    }

}
