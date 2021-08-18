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
    private T body;

    public static <K> ResponseResult<K> success(K body, String message) {
        return new ResponseResult<>(ResponseCode.SUCCESS,
                ResponseCode.SUCCESS.getState(), message, body);
    }

    public static <K> ResponseResult<K> fail(String message) {
        return new ResponseResult<>(ResponseCode.FAIL,
                ResponseCode.FAIL.getState(), message, null);
    }

    public enum ResponseCode {
        SUCCESS(200),
        FAIL(-1);

        private final int state;

        ResponseCode(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }

}
