package configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "com.pragma.operationsandexecution.loansanddeposits.application.usecase",
    includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*UseCase$")
)
public class UseCaseConfig {
}

