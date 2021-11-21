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
    void hasOrder() {
        System.out.println(orderService.hasOrder("sheng",3));
    }

    @Test
    void getOrderList() {
        for (BasicOrder order : orderService.getOrderList("sheng", "回收")) {
            System.out.println(order);
        }
    }

    @Test
    void getOrder() {
        System.out.println(orderService.getOrder("sheng",3));
    }

    @Test
    void addOrder() {
        Order order = new Order();
        order.setName("海豚");
        order.setLocation("火星");
        order.setPhoneNumber("18877866248");
        order.setType("购买");

        List<OrderGame> games = new ArrayList<>();

        OrderGame game1 = new OrderGame();
        game1.setId(1);
        game1.setNum(1);

        OrderGame game2 = new OrderGame();
        game2.setId(55);
        game2.setNum(1);

        games.add(game1);
        games.add(game2);

        order.setGames(games);
        orderService.addOrder("sheng", order);
    }
}