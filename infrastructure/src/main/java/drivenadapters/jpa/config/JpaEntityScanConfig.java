package drivenadapters.jpa.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static constants.CommonConstants.PACKAGE_ENTITY_MANAGERS_ENTITIES;
import static constants.CommonConstants.PACKAGE_INFRASTRUCTURE;

@Configuration
@EnableJpaRepositories(basePackages = PACKAGE_INFRASTRUCTURE)
@EntityScan(basePackages = PACKAGE_ENTITY_MANAGERS_ENTITIES)
public class JpaEntityScanConfig {
}

