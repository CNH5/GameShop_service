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
    void doLogin() {
        System.out.println(userService.doLogin("sheng", "sheng@123", null));
    }

    @Test
    void doRegister() {
        System.out.println(userService.doRegister("sheng", "sheng@123"));
    }

    @Test
    void doInfoUpdate() {
        User user = userService.getUser("sheng").getData();

        System.out.println(userService.doInfoUpdate(user, null));
    }

    @Test
    void queryUsers() {
        User user = new User();
        user.setGender("男");
        System.out.println("result:");
        for (User u : userService.doUserQuery(user, null).getData()) {
            System.out.println(u);
        }
    }
}