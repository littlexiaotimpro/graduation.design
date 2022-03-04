package com.whoai.blog.util;

import java.util.UUID;

/**
 * 为每一个请求存储一个唯一的 traceId，便于日志排查问题
 *
 * @date 2022/2/23
 * @see ThreadLocal
 * @see InheritableThreadLocal
 */
public class TraceUtil {

    public final static String TRACE_ID = "trace_id";
    private final static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void setTraceId(String traceId) {
        threadLocal.set(traceId);
    }

    public static String getTraceId() {
        String traceId = threadLocal.get();
        if (traceId == null) {
            traceId = UUID.randomUUID().toString().replaceAll("-","");
            setTraceId(traceId);
        }
        return traceId;
    }

    public static void removeTraceId() {
        threadLocal.remove();
    }

}
