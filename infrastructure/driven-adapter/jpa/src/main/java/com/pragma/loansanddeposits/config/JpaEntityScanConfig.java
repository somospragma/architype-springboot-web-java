package com.pragma.loansanddeposits.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static com.pragma.loansanddeposits.constant.JpaConstants.PACKAGE_INFRASTRUCTURE;


@Configuration
@EnableJpaRepositories(basePackages = PACKAGE_INFRASTRUCTURE)
@EntityScan(basePackages = PACKAGE_INFRASTRUCTURE)
public class JpaEntityScanConfig {
}

