package drivenadapters.mongo.loantrace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {
    "com.pragma.loansanddeposits.drivenadapters.mongo.loantrace.repository"
})
public class MongoLoanTraceConfig {
    // Configuración para que Spring escanee los repositorios de MongoDB de loantrace
}

