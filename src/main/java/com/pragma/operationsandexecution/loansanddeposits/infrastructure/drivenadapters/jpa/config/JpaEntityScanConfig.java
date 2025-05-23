package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.CommonConstants.PACKAGE_INFRASTRUCTURE;

@Configuration
@EntityScan(basePackages = {
    "com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.loan"
})
@EnableJpaRepositories(basePackages = PACKAGE_INFRASTRUCTURE)
public class JpaEntityScanConfig {
}

