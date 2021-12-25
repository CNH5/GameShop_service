package com.example.game_shop.handler;

import com.example.game_shop.Result.Result;
import com.example.game_shop.exception.AuthInconsistencyException;
import com.example.game_shop.exception.NullTokenException;
import com.example.game_shop.exception.TokenAuthExpiredException;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sheng
 * @date 2021/11/30 12:35
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 用户 token 过期
     */
    @ExceptionHandler(value = TokenAuthExpiredException.class)
    public Result<String> tokenExpiredExceptionHandler() {
        return ResultUtil.fail("登录已过期");
    }

    /**
     * 用户上传的token和账号不一致
     */
    @ExceptionHandler(value = AuthInconsistencyException.class)
    public Result<String> AuthInconsistencyExceptionHandler() {
        return ResultUtil.error("用户不一致");
    }

    /**
     * 用户上传的token为空
     */
    @ExceptionHandler(value = NullTokenException.class)
    public Result<String> NullTokenExceptionHandler() {
        return ResultUtil.fail("token为空");
    }
}
