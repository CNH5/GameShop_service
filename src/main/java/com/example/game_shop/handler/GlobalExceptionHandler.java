package com.example.game_shop.handler;

import com.example.game_shop.Result.Result;
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
    @ExceptionHandler(value = TokenAuthExpiredException.class)
    @ResponseBody
    public Result<String> tokenExpiredExceptionHandler(){
        System.out.println("用户 token 过期");
        return ResultUtil.fail("用户 token 过期");
    }
}
