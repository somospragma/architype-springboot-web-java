package com.pragma.operationsandexecution.loansanddeposits.architecture;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchitectureTest {
    JavaClasses domainClasses = new ClassFileImporter()
            .importPackages("com.pragma.operationsandexecution.loansanddeposits.domain");

    JavaClasses applicationClasses = new ClassFileImporter()
            .importPackages("com.pragma.operationsandexecution.loansanddeposits.application");

    @Test
    void domainShouldNotDependOnExternal() {
        noClasses()
                .that()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages("..domain..", "java..", "lombok..", "org.springframework.stereotype..")
                .as("Rule_1.0: Domain classes should not have any external dependencies")
                .check(domainClasses);
    }

    @Test
    void domainClassesShouldNotBeNamedWithTechSuffixes() {
        Stream.of("dto","DTO","Dto","request","REQUEST","Request","response","RESPONSE","Response", "Model")
                .reduce(classes().should().haveSimpleNameNotEndingWith("Dto"),
                        (cj, tool) -> cj.andShould().haveSimpleNameNotEndingWith(tool),
                        (a, b) -> b)
                .allowEmptyShould(true)
                .as("Rule_1.1: Domain classes should not be named with technology suffixes").check(domainClasses);
    }

    @Test
    void domainClassesShouldNotBeNamedWithToolNames() {
        Stream.of("rabbit","RABBIT","Rabbit","sqs","SQS","Sqs","sns","SNS","Sns","ibm","IBM","Ibm","dynamo"
                        ,"DYNAMO","Dynamo","aws","AWS","Aws","mysql","MYSQL","Mysql","postgres","POSTGRES","Postgres"
                        ,"redis","REDIS","Redis","mongo","MONGO","Mongo","rsocket","RSOCKET","Rsocket","r2dbc","R2DBC"
                        ,"R2dbc","http","HTTP","Http","kms","KMS","Kms","s3","S3","S3","graphql","GRAPHQL","Graphql"
                        ,"kafka","KAFKA","Kafka")
                .reduce(classes().should().haveSimpleNameNotContaining("rabbit"),
                        (cj, tool) -> cj.andShould().haveSimpleNameNotContaining(tool),
                        (a, b) -> b)
                .allowEmptyShould(true)
                .as("Rule_1.2: Domain classes should not be named with technology names").check(domainClasses);

    }
    @Test
    void useCaseFinalFields() {
        classes()
                .that()
                .resideInAnyPackage("..usecases..").and().haveSimpleNameNotEndingWith("Test")
                .should()
                .haveOnlyFinalFields()
                .allowEmptyShould(true)
                .as("Rule_2.0: UseCases should only have final attributes to avoid concurrency issues")
                .check(applicationClasses);
    }

    /*
    Spring annotations like @Component and @Service are excluded from the test
     */
    @Test
    void applicationClassesShouldOnlyDependOnModelLayer() {
        noClasses()
                .that()
                .haveSimpleNameNotEndingWith("Test")
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages("..domain..", "java..", "javax..", "..application..", "lombok.."
                        , "org.springframework.stereotype..")
                .as("Rule_2.1: UseCase classes should only depend on the model layer, excluding test classes")
                .check(applicationClasses);
    }

    @Test
    void classesPortsShouldHavePrefixI() {
        classes()
                .that()
                .resideInAnyPackage("..port..", "..ports..")
                .should()
                .haveSimpleNameStartingWith("I")
                .as("Rule_3.0: Classes in packages 'port' or 'ports' should have prefix 'I'")
                .check(new ClassFileImporter().importPackages("com.pragma.operationsandexecution"));
    }

}
