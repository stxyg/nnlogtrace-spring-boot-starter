package io.github.stxyg.nnlogtrace.annotation;

import org.apache.commons.lang.StringUtils;

/**
 * 登录用户的实现
 * 
 * @author stxyg
 * @since 2022/11/23
 **/
public interface LoginUserConfigurer {
    /**
     * 获得当前登录用户（中文）
     * 
     * @return 登录用户中文名
     */
    String getCurrentName();

    /**
     * 获得当前登录用户（英文）
     *
     * @return 登录用户英文名
     */
    default String getCurrentUserName() {
        return StringUtils.EMPTY;
    }
}
