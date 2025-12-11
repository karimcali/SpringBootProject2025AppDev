package ie.spring.planetsystem.SpaceManualImpl.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* ie.spring.planetsystem.SpaceManualImpl.service.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void beforeService(JoinPoint joinPoint) {
        log.info(" Before {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void afterReturningService(JoinPoint joinPoint, Object result) {
        log.info(" After {} returned {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void afterThrowingService(JoinPoint joinPoint, Throwable ex) {
        log.info(" Exception in {}: {}", joinPoint.getSignature(), ex.getMessage());
    }
}
