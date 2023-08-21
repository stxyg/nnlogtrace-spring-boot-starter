package io.github.stxyg.nnlogtrace.helper;

import java.util.UUID;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.MDC;

import io.github.stxyg.nnlogtrace.constants.LogTraceConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author stxyg
 * @since 2022/10/26
 **/
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogTraceHelper {

    public static void putLogTrace() {
        // 设置traceId
        try {
            String traceId = UUID.randomUUID().toString().replaceAll("-", "");
            MDC.put(LogTraceConstants.TRACE_ID, traceId);
        } catch (Exception e) {
            log.warn("设置traceId失败，e={}", ExceptionUtils.getStackTrace(e));
        }
    }


    public static void clearLogTrace() {
        try {
            MDC.remove(LogTraceConstants.TRACE_ID);
        } catch (Exception e) {
            log.warn("清空traceId失败，e={}", ExceptionUtils.getStackTrace(e));
        }
    }

}
