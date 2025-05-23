package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.mongo.loantrace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {
    "com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.mongo.loantrace"
})
public class MongoLoanTraceConfig {
    // Configuración para que Spring escanee los repositorios de MongoDB de loantrace
}

