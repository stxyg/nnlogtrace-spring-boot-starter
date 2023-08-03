package io.github.stxyg.nnlogtrace.autoconfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.github.stxyg.nnlogtrace.annotation.LogTraceAdvice;
import io.github.stxyg.nnlogtrace.helper.LogTraceHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述:
 *
 * @author stxyg
 * @since 2021/3/23
 */
@Slf4j
@Configuration
@AutoConfigureOrder(1)
public class LogTraceAutoConfiguration {

    @Configuration
    @ConditionalOnClass(WebMvcConfigurerAdapter.class)
    public class LogTraceConfigurer extends WebMvcConfigurerAdapter {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LogTraceInterceptor()).addPathPatterns("/**");
        }

    }

    @Configuration
    @ConditionalOnMissingClass("org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter")
    public class LogTraceConfiguration implements WebMvcConfigurer {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LogTraceInterceptor()).addPathPatterns("/**");
        }

    }

    public static class LogTraceInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            LogTraceHelper.putLogTrace();
            return super.preHandle(request, response, handler);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
            // 清空traceId
            LogTraceHelper.clearLogTrace();
        }
    }

    @Bean
    @ConditionalOnMissingBean(LogTraceAdvice.class)
    public LogTraceAdvice logTraceAdvice() {
        return new LogTraceAdvice();
    }
}
