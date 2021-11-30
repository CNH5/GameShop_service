package com.example.game_shop.service;

import com.example.game_shop.GameShopApplication;
import com.example.game_shop.pojo.BasicOrder;
import com.example.game_shop.pojo.Order;
import com.example.game_shop.pojo.OrderGame;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;


/**
 * @author sheng
 * @date 2021/11/19 9:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GameShopApplication.class)
class OrderServiceTest {
    @Resource
    private OrderService orderService;

    @Test
    void getOrderList() {
        for (BasicOrder order : orderService.getOrderList("sheng", "true", "回收", null).getData()) {
            System.out.println(order);
        }
    }

    @Test
    void getOrder() {
        System.out.println(orderService.getOrder("sheng", 3, null));
    }

}