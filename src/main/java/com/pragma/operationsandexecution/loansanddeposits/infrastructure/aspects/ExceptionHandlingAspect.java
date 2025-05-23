package com.pragma.operationsandexecution.loansanddeposits.infrastructure.aspects;

import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerPort;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.AspectsConstants.EXCEPTION_ERROR_CAPTURED;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.AspectsConstants.PACKAGE_POINT_CUT_DOMAIN_SERVICES;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.AspectsConstants.PACKAGE_POINT_CUT_INFRASTRUCTURE_REPOSITORIES;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.AspectsConstants.PACKAGE_POINT_CUT_APPLICATION_USE_CASES;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.AspectsConstants.UTILITY_APPLICATION_PACKAGE_POINT_CUT;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.AspectsConstants.UTILITY_APPLICATION_PACKAGE_POINT_CUT_THROWING;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.AspectsConstants.LAYER_CROSSCUTTING_ASPECTS_EXCEPTION_HANDLE_ASPECT;

/**
 * Aspecto para gestionar el envío de mensajes de error de manera centralizada en la aplicación.
 * <p>
 * Este aspecto intercepta las excepciones lanzadas por métodos en paquetes de servicios y repositorios
 * y envía mensajes de error de manera uniforme.
 * </p>
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ExceptionHandlingAspect {

    private final ILoggerPort loggerService;

    /**
     * Punto de corte para interceptar la ejecución de todos los métodos en paquetes de servicios y repositorios.
     */
    @Pointcut(PACKAGE_POINT_CUT_DOMAIN_SERVICES +
            PACKAGE_POINT_CUT_INFRASTRUCTURE_REPOSITORIES +
            PACKAGE_POINT_CUT_APPLICATION_USE_CASES)
    private void applicationPackagePointcut() {
    }

    /**
     * Advice que se ejecuta cuando se lanza una excepción en cualquier método interceptado.
     *
     * @param joinPoint el punto de unión que representa el método interceptado
     * @param throwable la excepción lanzada
     */
    @AfterThrowing(pointcut = UTILITY_APPLICATION_PACKAGE_POINT_CUT, throwing = UTILITY_APPLICATION_PACKAGE_POINT_CUT_THROWING)
    public void handleException(JoinPoint joinPoint, Throwable throwable) {
        loggerService.logError(joinPoint.getSignature().toShortString(), LAYER_CROSSCUTTING_ASPECTS_EXCEPTION_HANDLE_ASPECT,
                EXCEPTION_ERROR_CAPTURED, throwable.getMessage());
    }
}
