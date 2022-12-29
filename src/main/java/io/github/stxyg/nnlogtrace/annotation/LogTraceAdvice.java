package com.br.logtrace.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.br.logtrace.helper.LogTraceHelper;

/**
 * @author ningning.cheng
 * @since 2022/10/26
 **/
@Aspect
public class LogTraceAdvice {

    @Pointcut("@annotation(com.br.logtrace.annotation.LogTrace)")
    private void pointCut() {}


    @Before("pointCut()")
    public void LogTraceBefore(JoinPoint joinPoint) {
        LogTraceHelper.putLogTrace();
    }

    @After("pointCut()")
    public void LogTraceAfter() {
        LogTraceHelper.clearLogTrace();
    }

}
