package com.pragma.operationsandexecution.loansanddeposits.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.pragma.operationsandexecution.loansanddeposits.domain",
    includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Service$")
)
public class DomainServiceConfig {
}
