package com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.CommonConstants.PACKAGE_INFRASTRUCTURE_CONTROLLERS;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_COMPONENTS_INFO_DESCRIPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_COMPONENTS_INFO_TITLE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_GROUP;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_PATHS_TO_MATCH;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_INFO_TITLE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_INFO_DESCRIPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_INFO_VERSION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_INFO_CONTACT_NAME;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_INFO_CONTACT_URL;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.UTILITY_OPEN_API_INFO_CONTACT_EMAIL;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi() {
        final String[] packagesToScan = {PACKAGE_INFRASTRUCTURE_CONTROLLERS};
        return GroupedOpenApi
                .builder()
                .group(UTILITY_OPEN_API_GROUP)
                .packagesToScan(packagesToScan)
                .pathsToMatch(UTILITY_OPEN_API_PATHS_TO_MATCH)
                .addOpenApiCustomizer(statusApiCostumizer())
                .build();
    }

    private OpenApiCustomizer statusApiCostumizer() {
        return openAPI -> openAPI
                .info(new Info()
                        .title(UTILITY_OPEN_API_INFO_TITLE)
                        .description(UTILITY_OPEN_API_INFO_DESCRIPTION)
                        .version(UTILITY_OPEN_API_INFO_VERSION)
                        .contact(new Contact()
                                .name(UTILITY_OPEN_API_INFO_CONTACT_NAME)
                                .url(UTILITY_OPEN_API_INFO_CONTACT_URL)
                                .email(UTILITY_OPEN_API_INFO_CONTACT_EMAIL)));
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title(UTILITY_OPEN_API_COMPONENTS_INFO_TITLE).description(
                        UTILITY_OPEN_API_COMPONENTS_INFO_DESCRIPTION));
    }
}
