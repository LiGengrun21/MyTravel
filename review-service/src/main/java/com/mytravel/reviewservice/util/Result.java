package com.mytravel.reviewservice.util;

import lombok.Data;

/**
 * @author Li Gengrun
 * @date 2023/4/18 10:25
 *
 * 返回结果
 */
@Data
public class Result<T> {
    //操作代码
    Integer code;

    //提示信息
    String message;

    //结果数据
    T data;

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public Result(String message) {
        this.message = message;
    }

    public static Result SUCCESS() {
        return new Result(ResultCode.SUCCESS);
    }

    /**
     *
     * @param data 数据库返回的数据
     * @return
     * @param <T>
     */
    public static <T> Result SUCCESS(T data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    public static Result FAIL() {
        return new Result(ResultCode.FAIL);
    }

    public static Result FAIL(String message) {
        return new Result(ResultCode.FAIL, message);
    }
}
