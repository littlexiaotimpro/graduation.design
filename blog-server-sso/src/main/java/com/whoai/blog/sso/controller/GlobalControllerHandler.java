package com.whoai.blog.sso.controller;

import com.whoai.blog.bean.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(HttpServletRequest request, HttpServletResponse response, NullPointerException e) {
        return ResponseResult.fail(response.getStatus(), e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseResult<Void> handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        return ResponseResult.fail(e.getMessage());
    }

}
