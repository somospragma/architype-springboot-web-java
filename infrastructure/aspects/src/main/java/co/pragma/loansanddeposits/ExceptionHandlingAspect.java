package co.pragma.loansanddeposits;

import com.pragma.loansanddeposits.port.out.ILoggerPort;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
    @Pointcut(AspectsConstants.PACKAGE_POINT_CUT_DOMAIN_SERVICES +
            AspectsConstants.PACKAGE_POINT_CUT_INFRASTRUCTURE_REPOSITORIES +
            AspectsConstants.PACKAGE_POINT_CUT_APPLICATION_USE_CASES)
    private void applicationPackagePointcut() {
    }

    /**
     * Advice que se ejecuta cuando se lanza una excepción en cualquier método interceptado.
     *
     * @param joinPoint el punto de unión que representa el método interceptado
     * @param throwable la excepción lanzada
     */
    @AfterThrowing(pointcut = AspectsConstants.UTILITY_APPLICATION_PACKAGE_POINT_CUT, throwing = AspectsConstants.UTILITY_APPLICATION_PACKAGE_POINT_CUT_THROWING)
    public void handleException(JoinPoint joinPoint, Throwable throwable) {
        loggerService.logError(joinPoint.getSignature().toShortString(), AspectsConstants.LAYER_CROSSCUTTING_ASPECTS_EXCEPTION_HANDLE_ASPECT,
                AspectsConstants.EXCEPTION_ERROR_CAPTURED, throwable.getMessage());
    }
}
