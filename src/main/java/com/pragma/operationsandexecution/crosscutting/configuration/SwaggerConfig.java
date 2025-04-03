package com.pragma.operationsandexecution.crosscutting.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pragma.operationsandexecution.crosscutting.constants.common.CommonConstants.PACKAGE_INFRASTRUCTURE_CONTROLLERS;
import static com.pragma.operationsandexecution.crosscutting.constants.common.SwaggerConstants.*;

/**
 * Configuración de Swagger para la documentación de la API.
 * <p>
 * Esta clase configura las propiedades globales de la documentación OpenAPI,
 * incluyendo información general y detalles de contacto.
 * </p>
 */
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
