package io.github.stxyg.nnlogtrace.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import io.github.stxyg.nnlogtrace.autoconfig.LogRequestAutoConfiguration;

/**
 * 开启打印request日志
 * 
 * @author stxyg
 * @since 2.0.3
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {LogRequestAutoConfiguration.class})
public @interface EnableLogRequest {}
