package com.hzlei.config;

import com.hzlei.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author hzlei
 * @date 2021/05/06 13:52
 * Description  全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public Result<String> APIExceptionHandler(RuntimeException e) {
        log.error("捕获异常信息：" + e.toString());
        return Result.error("异常：" + e.getClass(), e.toString());
    }

    @ExceptionHandler(ApiException.class)
    public Result<String> APIExceptionHandler(ApiException e) {
        return Result.error(500, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到 ObjectError 对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return Result.error("参数校验失败", objectError.getDefaultMessage());
    }
}
