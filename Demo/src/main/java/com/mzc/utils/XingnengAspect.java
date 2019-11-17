package com.mzc.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component(value = "xnAspect")
@Aspect
public class XingnengAspect {

public Object around(ProceedingJoinPoint joinPoint){
    long start=System.currentTimeMillis();
    try {
        Object[] args = joinPoint.getArgs();
        Object proceed = joinPoint.proceed(args);
        return proceed;
    } catch (Throwable throwable) {
        throw new RuntimeException(throwable);
    }finally {
        long end=System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)+"ms");
    }
}



}
