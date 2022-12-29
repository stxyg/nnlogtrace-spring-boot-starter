![](https://img.shields.io/badge/version-2.0.0-red)
![](https://img.shields.io/badge/author-stxyg-yellow)
![](https://img.shields.io/badge/SpringBoot-1.5.13+-blue)

## 用途

1. 输出的日志自动添加traceId前缀或者后缀，便于追踪显示一个独立完整的请求日志链。
2. 前缀或者后缀形如：[traceId-73a2a686-7d1a-44c3-a872-ad537853fa51]
3. 打印请求url及操作人。

## 使用方法

### 快速开始

1. 引入依赖

```xml

<dependency>
   <groupId>io.github.stxyg</groupId>
   <artifactId>nnlogtrace-spring-boot-starter</artifactId>
   <version>latest</version>
</dependency>
```

2. application.properties 配置文件配置：

```properties
# 开启traceId打印
logtrace.enable=true
# 开启请求url，请求人的打印
log.request.enable=true
```

3. logback.xml 配置参数添加 %X{traceId}

### 进阶用法

#### traceId打印

常规用法仅支持web请求携带traceId，进阶用法将增加其他支持类型。

1. 支持<code>@Scheduled</code>调度方法或者其他非web请求携带<code>traceId</code>。标注<code>@LogTrace</code>注解即可，如

```
    @Scheduled(fixedRate = 5000)
    @LogTrace
    public void function1(){
    ...
    }
```

2. 支持子线程携带traceId。使用<code>AbstractLogTraceRunnable</code>代替<code>Runnable</code>,
   <code>AbstractLogTraceCallable</code>代替<code>Callable</code>即可，如

```
 executorService.execute(new AbstractLogTraceRunnable() {
      @Override
      public void logTraceRun() {
          try {
              log.info("多线程方法执行");
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  });
```

3. 支持@Async注解标注的方法携带traceId。需要自定义线程池时，指定ThreadFactory为<code>LogTraceThreadFactory</code>,如：

```
//定义线程池
@Configuration
public class SomeConfig {
   @Bean
   public ThreadPoolTaskExecutor myTask() {
      ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
      taskExecutor.setCorePoolSize(2);
      taskExecutor.setMaxPoolSize(10);
      taskExecutor.setQueueCapacity(10);
      taskExecutor.setKeepAliveSeconds(10);
      taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
      taskExecutor.setThreadFactory(new LogTraceThreadFactory("logTraceThreadPool-"));
      return taskExecutor;
   }
}

//使用@Async注解
@Async("myTask")
public void async() {
   log.info("async方法执行");
}
```

#### 请求url，请求人的打印

1. 开启即可打印请求url。
2. 某些请求url设置为不打印。

```properties
log.request.no-filter=/api/log/nofilter/**

```

注意：不写时，默认值为

```properties
["/swagger-resources", "/swagger-resources/**", "/v2/api-docs", "/v2/api-docs-ext","/error", "
/doc.html", "/webjars/**", "/ping"]
```

3. 如果想打印请求人，请实现配置接口<code>LoginUserConfigurer</code>自定义获取登录人的方式。如：

```java

import org.springframework.stereotype.Component;

import io.github.stxyg.nnlogtrace.annotation.LoginUserConfigurer;

/**
 * @author stxyg
 * @since 2022/11/23
 **/
@Component
public class LoginUserConfig implements LoginUserConfigurer {
   @Override
   public String getCurrentName() {
      return LoginUserHelper.getCurrentUserName();
   }
}

```

打印效果：
> 2022-12-23 20:14:39.941 [spring_test] [http-nio-28004-exec-3] INFO  [io.github.stxyg.nnlogtrace.autoconfig.LogRequestAutoConfiguration] -请求-URI：/api/users,queryString:null,currentUser
:小白

## 注意事项

使用log.xxx("")触发添加traceId，框架日志不参与。

```
<property name="ENCODER_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [${APP_NAME}] [%thread] %-5level [%logger] -%msg[traceId-%X{traceId}]%n"/>
```

