package com.pragma.loansanddeposits.port.out;

import java.util.Locale;

public interface IMessagePort {
    String getMessage(String key, Locale locale);
    String getMessage(String key);
}

