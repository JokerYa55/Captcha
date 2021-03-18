package app.aspect.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Логгирование контроллеров
 */
@Slf4j
@Aspect
@Configuration
public class LogControllerAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
        // Метод необходим для привязки аспекта
    }

    @Before("controller()")
    public void beforeController(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        log.info("Контроллер {} принял HTTP запрос:  {} {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                request.getMethod(),
                request.getRequestURL());
        log.info("Параметры, обрабатывемые контроллером :  {}", Arrays.toString(joinPoint.getArgs()));
    }

    @Around("controller()")
    public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch clock = new StopWatch(joinPoint.toString());
        try {
            clock.start(joinPoint.toShortString());
            return joinPoint.proceed();
        } finally {
            clock.stop();
            log.info(clock.prettyPrint());
        }
    }

    @AfterReturning(
            pointcut = "controller()",
            returning = "result")
    public void afterController(JoinPoint joinPoint, Object result) {

        log.info("Контроллер {} вернул  {} \n",
                joinPoint.getSignature().getDeclaringTypeName(),
                result);
    }
}
