package com.hzlei.utils;

/**
 * @author hzlei
 * @date 2021/04/07 11:44
 * Description  状态码
 */
public enum ResultCode {

    /**
     * 200 成功
     * 500 错误
     * 250 异常
     */
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR"),
    EXCEPTION(250, "EXCEPTION");

    private final int code;
    private final String desc;


    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}