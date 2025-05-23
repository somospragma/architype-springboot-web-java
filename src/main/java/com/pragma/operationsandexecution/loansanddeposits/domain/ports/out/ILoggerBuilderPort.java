package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import java.util.Map;

public interface ILoggerBuilderPort {

    Map<String, String> buildErrorWithLogWarning(String keyMessage, String valueMessage, String messageLog, String transactionId);

}
