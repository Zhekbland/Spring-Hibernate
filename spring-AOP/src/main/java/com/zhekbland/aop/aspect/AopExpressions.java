package com.zhekbland.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* *(..))")
    public void forDaoPackage() {

    }

    @Pointcut("execution(* get*(..))")
    public void getter() {

    }

    @Pointcut("execution(* set*(..))")
    public void setter() {

    }

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoNoGetterSetter() {

    }
}
