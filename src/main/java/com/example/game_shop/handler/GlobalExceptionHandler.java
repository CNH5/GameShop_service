package com.example.game_shop.handler;

import com.example.game_shop.Result.Result;
import com.example.game_shop.exception.AuthInconsistencyException;
import com.example.game_shop.exception.TokenAuthExpiredException;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sheng
 * @date 2021/11/30 12:35
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 用户 token 过期
     */
    @ResponseBody
    @ExceptionHandler(value = TokenAuthExpiredException.class)
    public Result<String> tokenExpiredExceptionHandler(){
        System.out.println("token 过期");
        return ResultUtil.fail("登录已过期");
    }

    /**
     * 用户上传的token和账号不一致
     */
    @ResponseBody
    @ExceptionHandler(value = AuthInconsistencyException.class)
    public Result<String> AuthInconsistencyExceptionHandler(){
        System.out.println("用户不一致");
        return ResultUtil.fail("用户不一致");
    }
}
