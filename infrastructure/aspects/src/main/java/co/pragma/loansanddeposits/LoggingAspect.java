package co.pragma.loansanddeposits;

import com.pragma.loansanddeposits.port.out.ILoggerPort;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



/**
 * Aspecto para gestionar el envío de mensajes de logging de manera centralizada en la aplicación.
 * <p>
 * Este aspecto intercepta la ejecución de métodos en paquetes de servicios y repositorios
 * para enviar mensajes de información, éxito y error de manera uniforme.
 * </p>
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final ILoggerPort loggerService;

    /**
     * Punto de corte para interceptar la ejecución de todos los métodos en paquetes de servicios, repositorios y casos de uso.
     */
    @Pointcut(AspectsConstants.PACKAGE_POINT_CUT_DOMAIN_SERVICES +
            AspectsConstants.PACKAGE_POINT_CUT_INFRASTRUCTURE_REPOSITORIES +
            AspectsConstants.PACKAGE_POINT_CUT_APPLICATION_USE_CASES)
    private void applicationPackagePointcut() {
    }

    /**
     * Advice que se ejecuta antes de la ejecución de cualquier método interceptado.
     *
     * @param joinPoint el punto de unión que representa el método interceptado
     */
    @Before(AspectsConstants.UTILITY_APPLICATION_PACKAGE_POINT_CUT)
    public void logBeforeMethod(JoinPoint joinPoint) {
        loggerService.logInfo(joinPoint.getSignature().toShortString(), AspectsConstants.LAYER_CROSSCUTTING_ASPECTS_LOG_BEFORE_METHOD,
                AspectsConstants.INIT_EXECUTION_METHOD, AspectsConstants.UTILITY_APPLICATION_PACKAGE_POINT_CUT);

    }

    /**
     * Advice que se ejecuta después de la ejecución exitosa de cualquier método interceptado.
     *
     * @param joinPoint el punto de unión que representa el método interceptado
     * @param result    el resultado de la ejecución del método
     */
    @AfterReturning(pointcut = AspectsConstants.UTILITY_APPLICATION_PACKAGE_POINT_CUT, returning = AspectsConstants.UTILITY_APPLICATION_PACKAGE_POINT_CUT_RESULT)
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        loggerService.logSuccess(joinPoint.getSignature().toShortString(), AspectsConstants.LAYER_CROSSCUTTING_ASPECTS_LOG_AFTER_METHOD,
                AspectsConstants.EXECUTED_METHOD_SUCCESSFUL, result);
    }
}
