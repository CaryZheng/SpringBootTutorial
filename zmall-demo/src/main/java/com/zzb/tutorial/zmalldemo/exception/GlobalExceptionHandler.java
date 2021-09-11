package com.zzb.tutorial.zmalldemo.exception;

import com.zzb.tutorial.zmalldemo.common.CodeEnum;
import com.zzb.tutorial.zmalldemo.common.ResponseWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenException.class)
    public ResponseWrapper handleTokenException(TokenException e) {
        return ResponseWrapper.fail(CodeEnum.CODE_000002);
    }

    @ExceptionHandler(Exception.class)
    public ResponseWrapper handleException(Exception e) {
        return ResponseWrapper.fail(CodeEnum.CODE_000001);
    }

}
