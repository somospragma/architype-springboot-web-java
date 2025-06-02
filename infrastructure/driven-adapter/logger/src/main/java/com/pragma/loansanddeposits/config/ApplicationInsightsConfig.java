package com.pragma.loansanddeposits.config;

import com.microsoft.applicationinsights.TelemetryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationInsightsConfig {
    @Bean
    public TelemetryClient telemetryClient() {
        return new TelemetryClient();
    }
}

