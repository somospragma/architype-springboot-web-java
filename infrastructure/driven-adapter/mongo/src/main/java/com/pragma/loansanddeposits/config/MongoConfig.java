package com.pragma.loansanddeposits.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {
    "com.pragma.loansanddeposits.loantrace",
})
public class MongoConfig {
    // Configuración para que Spring escanee los repositorios de MongoDB de loantrace
}

