package com.example.game_shop.controller;

import com.example.game_shop.pojo.User;
import com.example.game_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sheng
 * @date 2021/11/18 23:40
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestParam("account") String account, @RequestParam("password") String password) {
        if ("".equals(account) || "".equals(password)) {
            return "账号或密码不能为空";
        }

        String real_password = userService.getPassword(account);
        if (real_password != null) {
            if (real_password.equals(password)) {
                return "登陆成功";
            } else {
                return "密码错误";
            }
        } else {
            return "账号不存在";
        }
    }


    @ResponseBody
    @PostMapping("/register")
    public String register(@RequestParam("account") String account, @RequestParam("password") String password) {
        if ("".equals(account) || "".equals(password)) {
            return "账号或密码不能为空";
        }

        if (userService.noUser(account)) {
            return "账号已存在";

        } else {
            userService.addUser(account, password);
            return "注册成功";
        }
    }


    @GetMapping("/{account}/info")
    public User getInfo(@PathVariable String account) {
        return userService.getUser(account);
    }

    @ResponseBody
    @PostMapping("/info/update")
    public String updateInfo(@RequestBody User user) {
        //TODO: 信息校验, 校验修改的用户和上传的用户是否相同
        if (userService.noUser(user.getAccount())) {
            return "用户不存在";

        } else if (userService.hasName(user.getName())) {
            return "用户名已存在";

        } else {
            userService.updateInfo(user);
            return "修改成功";
        }
    }
}
