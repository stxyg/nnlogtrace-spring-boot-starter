package io.github.stxyg.nnlogtrace.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件对应的类
 * 
 * @author stxyg
 * @date 2021/1/7 11:04
 */
@Component
@ConfigurationProperties("logtrace")
public class LogTraceProperties {
    private boolean enable;

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
