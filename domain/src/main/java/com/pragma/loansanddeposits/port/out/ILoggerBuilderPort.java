package com.pragma.loansanddeposits.port.out;

import java.util.Map;

public interface ILoggerBuilderPort {

    Map<String, String> buildErrorWithLogWarning(String keyMessage, String valueMessage, String messageLog, String transactionId);

}
