package com.pragma.operationsandexecution.crosscutting.logging;

import java.util.Map;

public interface ILoggerBuilder {

    Map<String, String> buildErrorWithLogWarning(String keyMessage, String valueMessage, String messageLog, String transactionId);

}
