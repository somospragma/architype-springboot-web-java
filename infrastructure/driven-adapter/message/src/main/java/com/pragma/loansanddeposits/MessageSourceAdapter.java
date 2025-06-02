package com.pragma.loansanddeposits;

import com.pragma.loansanddeposits.port.out.IMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageSourceAdapter implements IMessagePort {
    private final MessageSource messageSource;

    @Override
    public String getMessage(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }
}

