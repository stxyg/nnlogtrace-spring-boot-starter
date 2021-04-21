package com.nn.logtrace.autoconfig;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nn.logtrace.constants.LogTraceConstants;

/**
 * 描述:
 *
 * @author stxyg
 * @create 2021/3/23
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "logtrace", name = "enable", havingValue = "true")
public class LogTraceAutoConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogTraceInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    public static class LogTraceInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            // 设置traceId
            try {
                String traceId = UUID.randomUUID().toString();
                MDC.put(LogTraceConstants.TRACE_ID, traceId);
            } catch (Exception e) {
                LogTraceAutoConfiguration.log.warn("设置traceId失败，e={}", ExceptionUtils.getStackTrace(e));
            }
            return super.preHandle(request, response, handler);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
            // 清空traceId
            try {
                MDC.remove(LogTraceConstants.TRACE_ID);
            } catch (Exception e) {
                LogTraceAutoConfiguration.log.warn("清空traceId失败，e={}", ExceptionUtils.getStackTrace(e));
            }
        }
    }
}
