用途：
方便根据traceId查询日志的上下文信息。

使用方法：
1. 克隆源码到本地，编译打包发布到内部仓库或者下载jar文件使用
2. 引入依赖
```
<dependency>
 <groupId>com.nn</groupId>
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

