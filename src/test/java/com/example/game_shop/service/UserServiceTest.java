package com.example.game_shop.service;

import com.example.game_shop.GameShopApplication;
import com.example.game_shop.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author sheng
 * @date 2021/11/19 9:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GameShopApplication.class)
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void noUser() {
        System.out.println(userService.noUser("sheng"));
    }

    @Test
    void hasName() {
        System.out.println(userService.hasName("sheng"));
    }

    @Test
    void getUser() {
        System.out.println(userService.getUser("sheng"));
    }

    @Test
    void addUser() {
        String account = "sheng";
        String password = "sheng@123";
        userService.addUser(account, password);
    }

    @Test
    void getPassword() {
        System.out.println(userService.getPassword("sheng"));
    }

    @Test
    void updateInfo() {
        String account = "sheng";
        User user = userService.getUser(account);
        user.setGender("男");
        user.setName("Ilex-179");
        userService.updateInfo(user);
        System.out.println(userService.getUser(account));
    }

    @Test
    void queryUsers() {
        User user = new User();
        user.setGender("男");
        System.out.println("result:");
        for (User u : userService.queryUsers(user)){
            System.out.println(u);
        }
    }
}