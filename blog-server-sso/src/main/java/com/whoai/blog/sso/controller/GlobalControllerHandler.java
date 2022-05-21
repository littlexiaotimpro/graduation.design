package com.whoai.blog.sso.controller;

import com.whoai.blog.bean.ResponseResult;
import com.whoai.blog.sso.exception.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerHandler {

    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(HttpServletRequest request, HttpServletResponse response, BadCredentialsException e) {
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(value = {UserDisableException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(HttpServletRequest request, HttpServletResponse response, UserDisableException e) {
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(value = {UserAccountLockedException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(HttpServletRequest request, HttpServletResponse response, UserAccountLockedException e) {
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(value = {UserAccountExpiredException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(HttpServletRequest request, HttpServletResponse response, UserAccountExpiredException e) {
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(value = {UserCredentialsExpiredException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(HttpServletRequest request, HttpServletResponse response, UserCredentialsExpiredException e) {
        return ResponseResult.fail(e.getMessage());
    }

    @ExceptionHandler(value = {UserManagerException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(HttpServletRequest request, HttpServletResponse response, UserManagerException e) {
        return ResponseResult.fail(e.getMessage());
    }

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
