package com.gyanesh.autocompletion.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAOP {

    private static final Logger logger = LoggerFactory.getLogger ( LoggerAOP.class );

    @AfterThrowing("execution(* com.gyanesh.autocompletion.*.*.get*(..)))")
    public void afterThrowingExceptionGetters ( JoinPoint joinPoint ) {
        logger.info ( "afterThrowingExceptionGetters:Error was raised for a Method Invoked: " + joinPoint.getSignature ().getName () );
        logger.info ( "afterThrowingExceptionGetters:Values Passed: " );
        for (Object obj : joinPoint.getArgs ()) {
            logger.info ( ":" + obj );
        }
    }

    @Before("execution(* com.gyanesh.autocompletion.*.*.get*(..)))")
    public void beforeGetters ( JoinPoint joinPoint ) {
        logger.info ( "beforeGetters:Error was raised for a Method Invoked: " + joinPoint.getSignature ().getName () );
        logger.info ( "beforeGetters::Values Passed: " );
        for (Object obj : joinPoint.getArgs ()) {
            logger.info ( ":" + obj );
        }
    }

}