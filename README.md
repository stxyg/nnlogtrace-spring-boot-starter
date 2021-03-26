用途：
方便根据traceId查询上下文的日志信息。

使用方法：
1. 引入依赖
2. application.properties  配置文件配置：
   > logtrace.enable=true (开启)
3. logback.xml 配置参数添加  %X{traceId}

