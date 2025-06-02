package com.pragma.loansanddeposits.config;

import org.apache.coyote.AbstractProtocol;
import org.apache.coyote.ProtocolHandler;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@Profile("local")
public class VirtualThreadAsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        return java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> protocolHandlerVirtualThreadExecutorCustomizer() {
        return factory -> factory.addProtocolHandlerCustomizers(
                (TomcatProtocolHandlerCustomizer<ProtocolHandler>) protocolHandler -> {
                    if (protocolHandler instanceof AbstractProtocol<?> abstractProtocol) {
                        abstractProtocol.setExecutor(java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor());
                    }
                }
        );
    }
}

