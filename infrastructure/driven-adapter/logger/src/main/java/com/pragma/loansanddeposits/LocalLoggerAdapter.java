package com.pragma.loansanddeposits;

import com.pragma.loansanddeposits.port.out.ILoggerBuilderPort;
import com.pragma.loansanddeposits.port.out.ILoggerPort;
import com.pragma.loansanddeposits.valueobject.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Profile("local")
@RequiredArgsConstructor
public class LocalLoggerAdapter implements ILoggerPort, ILoggerBuilderPort {

    public static final String THREAD_INFO_NAME_IS_VIRTUAL = "Thread info: name={}, isVirtual={}";

    @Override
    @Async
    public void logMessage(MessageType type, String message) {
        log.info(THREAD_INFO_NAME_IS_VIRTUAL, Thread.currentThread().getName(), Thread.currentThread().isVirtual());
        switch (type) {
            case INFO -> log.info(message);
            case WARNING -> log.warn(message);
            case ERROR -> log.error(message);
            case SUCCESS -> log.info("SUCCESS: {}", message);
            default -> log.debug(message);
        }
    }

    @Override
    @Async
    public void logInfo(String transactionId, String layer, String message, Object additionalData) {
        log.info(THREAD_INFO_NAME_IS_VIRTUAL, Thread.currentThread().getName(), Thread.currentThread().isVirtual());
        log.info(buildLog(transactionId, layer, message, additionalData).toString());
    }

    @Override
    @Async
    public void logWarning(String transactionId, String layer, String message, Object additionalData) {
        log.info(THREAD_INFO_NAME_IS_VIRTUAL, Thread.currentThread().getName(), Thread.currentThread().isVirtual());
        log.warn(buildLog(transactionId, layer, message, additionalData).toString());
    }

    @Override
    @Async
    public void logError(String transactionId, String layer, String message, Object additionalData) {
        log.info(THREAD_INFO_NAME_IS_VIRTUAL, Thread.currentThread().getName(), Thread.currentThread().isVirtual());
        log.error(buildLog(transactionId, layer, message, additionalData).toString());
    }

    @Override
    @Async
    public void logSuccess(String transactionId, String layer, String message, Object additionalData) {
        log.info(THREAD_INFO_NAME_IS_VIRTUAL, Thread.currentThread().getName(), Thread.currentThread().isVirtual());
        log.info("SUCCESS: {}", buildLog(transactionId, layer, message, additionalData));
    }

    @Override
    public Map<String, String> buildErrorWithLogWarning(String keyMessage, String valueMessage,
                                                        String messageLog, String transactionId) {
        Map<String, String> errors = new HashMap<>();
        errors.put(keyMessage, valueMessage);
        logWarning(transactionId, "LOGGER_BUILD_LOG", messageLog, errors);
        return errors;
    }

    private StringBuilder buildLog(String transactionId, String layer, String message, Object additionalData) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("transactionId=").append(transactionId).append(" ");
        logMessage.append("layer=").append(layer).append(" ");
        logMessage.append("message=").append(message).append(" ");
        logMessage.append("additionalData=").append(additionalData);
        return logMessage;
    }
}

