package com.whoai.blog.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 包装的请求响应结果
 */
@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 2771639845789215377L;

    private String code;
    private int state;
    private Message message;
    private T body;

    public enum ResponseCode {
        SUCCESS,
        FAIL,
    }

}
