package com.hzlei.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author hzlei
 * @date 2021/04/07 11:44
 * Description  统一返回结果
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Result<T> implements Serializable {

    private int code;
    private String nextPage;
    private String message;
    private T data;
    /**
     * 分页信息
     */
    private PageResponse pageInfo;

    /**
     * 状态码
     *
     * @param code 状态码
     */
    private Result(int code) {
        this.code = code;
        this.message = "加载成功!";
    }

    /**
     * 状态码 + 数据
     *
     * @param code 状态码
     * @param data   数据
     */
    private Result(int code, T data) {
        this.code = code;
        this.data = data;
        this.message = "加载成功!";
    }

    /**
     * 状态码 + 数据 + 分页
     *
     * @param code   状态码
     * @param code   分页
     * @param nextPage 下一页
     */
    private Result(int code, T data, String nextPage) {
        this.code = code;
        this.data = data;
        this.nextPage = nextPage;
        this.message = "加载成功!";
    }

    public Result(int code, T data, PageResponse pageInfo) {
        this.code = code;
        this.data = data;
        this.pageInfo = pageInfo;
        this.message = "加载成功!";
    }

    /**
     * 状态码 + 消息 + 数据
     *
     * @param code 状态码
     * @param message    消息
     * @param data   数据
     */
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 状态码 + 消息
     *
     * @param code 状态码
     * @param message    消息
     */
    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 使之不在json序列化结果当中
     */
    public boolean isSuccess() {
        return this.code == ResultCode.SUCCESS.getCode();
    }

    public int getcode() {
        return code;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public T getData() {
        return data;
    }

    public String getmessage() {
        return message;
    }

    public PageResponse getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageResponse pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * 返回成功-无参数
     *
     * @return
     */
    public static <T> Result<T> success() {
        return new Result<T>(ResultCode.SUCCESS.getCode());
    }


    /**
     * 返回成功-消息
     *
     * @param message 返回消息
     * @return
     */
    public static <T> Result<T> success(String message) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), message);
    }

    /**
     * 返回成功-数据
     *
     * @param data 返回数据
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), data);
    }

    /**
     * 返回成功-数据 + 分页
     *
     * @param data     返回数据
     * @param nextPage 下一页
     * @return
     */
    public static <T> Result<T> success(T data, String nextPage) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), data, nextPage);
    }

    /**
     * 返回成功-数据 + 分页
     *
     * @param data     返回数据
     * @param pageInfo 分页数据
     * @return
     */
    public static <T> Result<T> success(T data, PageResponse pageInfo) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), data, pageInfo);
    }

    /**
     * 返回成功-消息 + 数据
     *
     * @param message  返回消息
     * @param data 返回数据
     * @return
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), message, data);
    }


    /**
     * 返回错误-无参数
     *
     * @return
     */
    public static <T> Result<T> error() {
        return new Result<T>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getDesc());
    }

    /**
     * 返回异常-无参数
     *
     * @return
     */
    public static <T> Result<T> exception() {
        return new Result<T>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getDesc());
    }


    /**
     * 返回错误-消息
     *
     * @param errorMessage 错误消息
     * @return
     */
    public static <T> Result<T> error(String errorMessage) {
        return new Result<T>(ResultCode.ERROR.getCode(), errorMessage);
    }

    /**
     * 返回错误-错误码 + 消息
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @return
     */
    public static <T> Result<T> error(int errorCode, String errorMessage) {
        return new Result<T>(errorCode, errorMessage);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}