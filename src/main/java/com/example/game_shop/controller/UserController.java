package com.example.game_shop.controller;

import com.example.game_shop.Result.Result;
import com.example.game_shop.annotation.DoWithoutToken;
import com.example.game_shop.annotation.RequiredPermission;
import com.example.game_shop.pojo.User;
import com.example.game_shop.service.UserService;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


    @DoWithoutToken
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

    @DoWithoutToken
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
    @RequiredPermission
    public Result<List<User>> queryUser(User queryForm) {
        try {
            return userService.doUserQuery(queryForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @ResponseBody
    @PostMapping("/info/update")
    public Result<Integer> updateInfo(@RequestBody User user) {
        try {
            return userService.doInfoUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }
}
