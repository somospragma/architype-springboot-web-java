package com.pragma.loansanddeposits;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.SeverityLevel;
import com.microsoft.applicationinsights.telemetry.TraceTelemetry;
import com.pragma.loansanddeposits.constant.LoggerConstants;
import com.pragma.loansanddeposits.port.out.ILoggerBuilderPort;
import com.pragma.loansanddeposits.port.out.ILoggerPort;
import com.pragma.loansanddeposits.valueobject.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.pragma.loansanddeposits.constant.AzureTelemetryConstants.LOG_DEBUG;
import static com.pragma.loansanddeposits.constant.AzureTelemetryConstants.LOG_ERROR;
import static com.pragma.loansanddeposits.constant.AzureTelemetryConstants.LOG_INFO;
import static com.pragma.loansanddeposits.constant.AzureTelemetryConstants.LOG_SUCCESS;
import static com.pragma.loansanddeposits.constant.AzureTelemetryConstants.LOG_WARNING;

/**
 * Implementación de {@link ILoggerPort} utilizando Azure Monitor (Application Insights).
 * <p>
 * Esta clase envía logs a Azure Monitor utilizando el SDK de Application Insights.
 * </p>
 */
@Component
@Profile("!local")
@RequiredArgsConstructor
public class AzureTelemetryLoggerAdapter implements ILoggerPort, ILoggerBuilderPort {

    private final TelemetryClient telemetryClient;

    private static final Map<MessageType, String> LOG_LEVELS = Map.of(
            MessageType.INFO, LOG_INFO,
            MessageType.WARNING, LOG_WARNING,
            MessageType.ERROR, LOG_ERROR,
            MessageType.SUCCESS, LOG_SUCCESS
    );
    private static final Map<MessageType, SeverityLevel> SEVERITY_LEVELS = Map.of(
            MessageType.INFO, com.microsoft.applicationinsights.telemetry.SeverityLevel.Information,
            MessageType.WARNING, com.microsoft.applicationinsights.telemetry.SeverityLevel.Warning,
            MessageType.ERROR, com.microsoft.applicationinsights.telemetry.SeverityLevel.Error,
            MessageType.SUCCESS, com.microsoft.applicationinsights.telemetry.SeverityLevel.Information
    );

    /**
     * Convierte el tipo de mensaje a nivel de log de SLF4J.
     *
     * @param type el tipo de mensaje
     * @return el nivel de log correspondiente
     */
    private String getLogLevel(MessageType type) {
        return LOG_LEVELS.getOrDefault(type, LOG_DEBUG);
    }

    /**
     * Mapea {@link MessageType} a {@link com.microsoft.applicationinsights.telemetry.SeverityLevel}.
     *
     * @param type el tipo de mensaje
     * @return el nivel de severidad correspondiente
     */
    private SeverityLevel getSeverityLevel(MessageType type) {
        return SEVERITY_LEVELS.getOrDefault(type, com.microsoft.applicationinsights.telemetry.SeverityLevel.Verbose);
    }

    /**
     * Envía un mensaje de telemetría a Azure Monitor.
     *
     * @param type    el tipo de mensaje
     * @param message el contenido del mensaje
     */
    @Override
    public void logMessage(MessageType type, String message) {
        TraceTelemetry telemetry = new TraceTelemetry();
        telemetry.setMessage(message);
        telemetry.setSeverityLevel(getSeverityLevel(type));
        telemetryClient.trackTrace(telemetry);
    }

    @Override
    public void logInfo(String transactionId, String layer, String message, Object additionalData) {
        logMessage(MessageType.INFO, buildLog(transactionId, layer, message, additionalData).toString());
    }

    @Override
    public void logWarning(String transactionId, String layer, String message, Object additionalData) {
        logMessage(MessageType.WARNING, buildLog(transactionId, layer, message, additionalData).toString());
    }

    @Override
    public void logError(String transactionId, String layer, String message, Object additionalData) {
        logMessage(MessageType.ERROR, buildLog(transactionId, layer, message, additionalData).toString());
    }

    @Override
    public void logSuccess(String transactionId, String layer, String message, Object additionalData) {
        logMessage(MessageType.SUCCESS, buildLog(transactionId, layer, message, additionalData).toString());
    }

    @Override
    public Map<String, String> buildErrorWithLogWarning(String keyMessage, String valueMessage, String messageLog, String transactionId) {
        Map<String, String> errors = new HashMap<>();
        errors.put(keyMessage, valueMessage);
        logWarning(transactionId, LoggerConstants.LAYER_CROSSCUTTING_LOGGER_BUILD_LOG,
                messageLog, errors);
        return errors;
    }

    private StringBuilder buildLog(String transactionId, String layer, String message, Object additionalData) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(LoggerConstants.LOG_TRANSACTION_ID).append(transactionId).append(LoggerConstants.LOG_SPACE);
        logMessage.append(LoggerConstants.LOG_LAYER).append(layer).append(LoggerConstants.LOG_SPACE);
        logMessage.append(LoggerConstants.LOG_MESSAGE).append(message).append(LoggerConstants.LOG_SPACE);
        logMessage.append(LoggerConstants.LOG_ADDITIONAL_DATA).append(additionalData);

        return logMessage;
    }

}
