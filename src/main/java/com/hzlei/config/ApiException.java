package com.hzlei.config;

import lombok.Getter;

/**
 * @author hzlei
 * @date 2021/05/06 13:44
 * Description  全局异常
 */
@Getter
public class ApiException extends RuntimeException {

    private int code;
    private String message;

    public ApiException() {
        this(500, "接口错误");
    }

    public ApiException(String message) {
        this(500, message);
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
