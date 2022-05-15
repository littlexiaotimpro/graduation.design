package com.whoai.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 包装的请求响应结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 2771639845789215377L;

    private ResponseCode code;
    private int state;
    private String message;
    private boolean success;
    private T body;

    public static <K> ResponseResult<K> success(K body, String message) {
        return new ResponseResult<>(ResponseCode.OK,
                ResponseCode.OK.getState(), message, true, body);
    }

    public static <K> ResponseResult<K> success(int state, K body, String message) {
        return new ResponseResult<>(ResponseCode.OK,
                state, message, true, body);
    }

    public static <K> ResponseResult<K> fail(String message) {
        return new ResponseResult<>(ResponseCode.FAIL,
                ResponseCode.FAIL.getState(), message, false, null);
    }

    public static <K> ResponseResult<K> fail(int state, String message) {
        return new ResponseResult<>(ResponseCode.FAIL,
                state, message, false, null);
    }

    public static <K> ResponseResult<K> forbidden(String message) {
        return new ResponseResult<>(ResponseCode.FAIL,
                ResponseCode.FORBIDDEN.state, message, false, null);
    }

    public static <K> ResponseResult<K> unauthorized(String message) {
        return new ResponseResult<>(ResponseCode.FAIL,
                ResponseCode.UNAUTHORIZED.state, message, false, null);
    }

    public enum ResponseCode {
        OK(200, "OK"),
        FAIL(-1, "FAIL"),
        FORBIDDEN(403, "Forbidden"),
        UNAUTHORIZED(401, "Unauthorized");

        private final int state;
        private final String desc;

        ResponseCode(int state, String desc) {
            this.state = state;
            this.desc = desc;
        }

        public int getState() {
            return state;
        }

        public String getDesc() {
            return desc;
        }
    }

}
