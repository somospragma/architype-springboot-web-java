package com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants;

public class AspectsConstants {

    /*  Constantes de errores */
    public static final String EXCEPTION_ERROR_CAPTURED = "Error capturado";

    /*  Constantes de Package */
    public static final String PACKAGE_POINT_CUT_DOMAIN_SERVICES = "execution(* com.pragma.operationsandexecution.loansanddeposits.domain.services..*(..)) || ";
    public static final String PACKAGE_POINT_CUT_INFRASTRUCTURE_REPOSITORIES = "execution(* com.pragma.operationsandexecution.loansanddeposits.infrastructure.repositories..*(..)) || ";
    public static final String PACKAGE_POINT_CUT_APPLICATION_USE_CASES = "execution(* com.pragma.operationsandexecution.loansanddeposits.application.usecases..*(..))";

    /* Constantes Extras de utilidad */
    public static final String UTILITY_APPLICATION_PACKAGE_POINT_CUT = "applicationPackagePointcut()";
    public static final String UTILITY_APPLICATION_PACKAGE_POINT_CUT_THROWING = "ex";
    public static final String UTILITY_APPLICATION_PACKAGE_POINT_CUT_RESULT = "result";

    /* Constantes de información de flujo */
    public static final String INIT_EXECUTION_METHOD = "Ejecución del metodo iniciado";
    public static final String EXECUTED_METHOD_SUCCESSFUL = "Método ejecutado exitosamente";

    /* Constantes de layers */
    public static final String LAYER_CROSSCUTTING_ASPECTS_LOG_BEFORE_METHOD = "crosscutting.aspects.logBeforeMethod";
    public static final String LAYER_CROSSCUTTING_ASPECTS_EXCEPTION_HANDLE_ASPECT = "crosscutting.aspects.ExceptionHandlingAspect.handleException";
    public static final String LAYER_CROSSCUTTING_ASPECTS_LOG_AFTER_METHOD = "crosscutting.aspects.logAfterMethod";

    private AspectsConstants() {
    }
}
