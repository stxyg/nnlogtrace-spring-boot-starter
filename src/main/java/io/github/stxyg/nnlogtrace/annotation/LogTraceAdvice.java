package io.github.stxyg.nnlogtrace.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import io.github.stxyg.nnlogtrace.helper.LogTraceHelper;

/**
 * @author stxyg
 * @since 2022/10/26
 **/
@Aspect
public class LogTraceAdvice {

    @Pointcut("@annotation(io.github.stxyg.nnlogtrace.annotation.LogTrace)")
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
