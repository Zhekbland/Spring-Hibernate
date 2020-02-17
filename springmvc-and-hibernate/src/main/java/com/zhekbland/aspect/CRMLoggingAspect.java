package com.zhekbland.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    private final Logger logger = Logger.getLogger(CRMLoggingAspect.class.getName());

    @Pointcut("execution(* com.zhekbland.presentation.*.*(..))")
    private void forControllerPackage() {

    }

    @Pointcut("execution(* com.zhekbland.dao.*.*(..))")
    private void forDaoPackage() {

    }

    @Pointcut("execution(* com.zhekbland.service.*.*(..))")
    private void forServicePackage() {

    }

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {

    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("in @Before: calling method: " + method);
        Object[] args = joinPoint.getArgs();
        for (Object tempArg : args) {
            logger.info("argument: " + tempArg.toString());
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("in @AfterReturning: from method: " + method);
        logger.info("result: " + result);
    }
}
