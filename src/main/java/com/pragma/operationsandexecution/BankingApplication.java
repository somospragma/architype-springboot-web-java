package com.pragma.operationsandexecution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.CommonConstants.PACKAGE_ENTITY_MANAGERS_ENTITIES;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.CommonConstants.PACKAGE_INFRASTRUCTURE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.CommonConstants.PACKAGE_PROJECT_ROOT;

/**
 * Clase principal de la aplicación.
 * <p>
 * Esta clase es el punto de entrada de la aplicación Spring Boot. Configura el escaneo de componentes,
 * entidades y repositorios necesarios para interactuar con ellos.
 * </p>
 *
 * @SpringBootApplication combina @Configuration, @EnableAutoConfiguration y @ComponentScan,
 * indicando a Spring Boot que escanee los componentes en el paquete base especificado.
 * @EntityScan especifica los paquetes donde se encuentran las entidades JPA.
 * @EnableCosmosRepositories habilita los repositorios de Spring Data para Cosmos DB en el paquete indicado.
 */
@SpringBootApplication(scanBasePackages = PACKAGE_PROJECT_ROOT)
@EntityScan(basePackages = PACKAGE_ENTITY_MANAGERS_ENTITIES)
@EnableMongoRepositories(basePackages = PACKAGE_INFRASTRUCTURE)
public class BankingApplication {

    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Este método invoca {@link SpringApplication#run(Class, String[])}, lo que inicia el contexto de Spring Boot
     * y configura la aplicación según las anotaciones y configuraciones proporcionadas.
     * </p>
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
         SpringApplication.run(BankingApplication.class, args);
    }

}
