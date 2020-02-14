package com.zhekbland.aop.aspect;

import com.zhekbland.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

    private Logger logger = Logger.getLogger(MyLoggingAspect.class.getName());

    @Around("execution(* com.zhekbland.aop.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\nExecuting @Around on method: " + method);

        long begin = System.currentTimeMillis();
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            logger.info(e.getMessage());
            result = "Major accident! But no worries...";
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;
        logger.info("\nDuration: " + duration / 1000.0 + " seconds");
        return result;
    }

    @After("execution(* com.zhekbland.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\nExecuting @After (finally) on method: " + method);
    }

    @AfterThrowing(pointcut = "execution(* com.zhekbland.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theExc) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\nExecuting @AfterThrowing on method: " + method);

        System.out.println("The exception is: " + theExc);
    }

    @Before("com.zhekbland.aop.aspect.AopExpressions.forDaoNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("Executing @Before advice");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            System.out.println(tempArg);
            if (tempArg instanceof Account) {
                Account account = (Account) tempArg;
                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());
            }
        }

    }

    @AfterReturning(pointcut = "execution(* com.zhekbland.aop.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n Executing @AfterReturning on method: " + method);
        System.out.println("\n result is: " + result);
        converAccountNameToUpperCase(result);
        System.out.println("\n result after modify is: " + result);
    }

    private void converAccountNameToUpperCase(List<Account> result) {
        result.stream().peek(account -> account.setName(account.getName().toUpperCase())).collect(Collectors.toList());
    }

}
