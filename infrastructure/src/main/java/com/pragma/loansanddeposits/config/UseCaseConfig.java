package com.pragma.loansanddeposits.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.pragma.loansanddeposits.usecase",
    includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*UseCase$")
)
public class UseCaseConfig {
}

