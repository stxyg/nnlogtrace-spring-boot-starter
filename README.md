用途：
方便根据traceId查询日志的上下文信息，目前只支持WEB请求

使用方法：
1. 引入依赖
```
<dependency>
 <groupId>io.github.stxyg</groupId>
  <artifactId>nnlogtrace-spring-boot-starter</artifactId>
  <version>1.0.0-RELEASE</version>
</dependency>
```
3. application.properties  配置文件配置：
```
 logtrace.enable=true (开启)
```
4. logback.xml 配置参数添加  %X{traceId}，如：
```
%d{yyyy-MM-dd HH:mm:ss.SSS} [${APP_NAME}] [%thread] %-5level [%logger] -%msg[traceId-%X{traceId}]%n
```

