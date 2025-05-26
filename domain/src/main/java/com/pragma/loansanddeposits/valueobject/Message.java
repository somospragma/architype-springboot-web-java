package com.pragma.loansanddeposits.valueobject;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Representa un mensaje dentro de la aplicación.
 * <p>
 * Esta clase encapsula la información de un mensaje, incluyendo su contenido, tipo y marca de tiempo.
 * </p>
 *
 * @param <T> el tipo de contenido del mensaje
 */
@Getter
@Setter
public class Message<T> {
    
    /**
     * Tipo del mensaje (información, advertencia, error, entre otros.).
     */
    private MessageType type;
    
    /**
     * Contenido del mensaje.
     */
    private T content;
    
    /**
     * Fecha y hora en que se creó el mensaje.
     */
    private final LocalDateTime timestamp;
    
    /**
     * Constructor para crear un mensaje con tipo y contenido.
     *
     * @param type    el tipo de mensaje
     * @param content el contenido del mensaje
     */
    public Message(MessageType type, T content) {
        this.type = type;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

}
