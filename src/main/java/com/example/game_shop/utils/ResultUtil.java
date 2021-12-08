package com.example.game_shop.utils;

import com.example.game_shop.Result.Result;
import com.example.game_shop.Result.ResultEnum;

/**
 * @author sheng
 * @date 2021/11/29 23:36
 */
public class ResultUtil {
    public static <T> Result<T> defineSuccess(int code, T data) {
        Result<T> result = new Result<>();
        return result.setCode(code).setData(data);
    }

    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        return result.setCode(ResultEnum.SUCCESS.code).setMsg(msg).setData(data);
    }


    public static <T> Result<T> defineFail(int code, T data) {
        Result<T> result = new Result<>();
        return result.setCode(code).setData(data);
    }

    public static <T> Result<T> fail(String msg) {
        return fail(msg, null);
    }

    public static <T> Result<T> fail(String msg, T data) {
        Result<T> result = new Result<>();
        return result.setCode(ResultEnum.FAIL.code).setMsg(msg).setData(data);
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        return result.setCode(ResultEnum.INTERNAL_SERVER_ERROR.code).setMsg(msg);
    }


    public static <T> Result<T> define(int code, String msg, T data) {
        Result<T> result = new Result<>();
        return result.setCode(code).setMsg(msg).setData(data);
    }
}
