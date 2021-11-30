package com.example.game_shop.controller;

import com.example.game_shop.Result.Result;
import com.example.game_shop.pojo.User;
import com.example.game_shop.service.UserService;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 23:40
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping("/login")
    public Result<String> login(@RequestParam("account") String account,
                                @RequestParam("password") String password,
                                HttpServletResponse response) {
        try {
            return userService.doLogin(account, password, response);
        } catch (Exception e) {
            // 系统出错
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/register")
    public Result<String> register(@RequestParam("account") String account,
                                   @RequestParam("password") String password) {
        try {
            return userService.doRegister(account, password);
        } catch (Exception e) {
            // 系统出错
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @GetMapping("/{account}/info")
    public Result<User> getInfo(@PathVariable String account) {
        try {
            return userService.getUser(account);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @GetMapping("query")
    public Result<List<User>> queryUser(User queryForm, HttpServletRequest request) {
        try {
            return userService.doUserQuery(queryForm, request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @ResponseBody
    @PostMapping("/info/update")
    public Result<String> updateInfo(@RequestBody User user, HttpServletRequest request) {
        try {
            return userService.doInfoUpdate(user, request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }
}
