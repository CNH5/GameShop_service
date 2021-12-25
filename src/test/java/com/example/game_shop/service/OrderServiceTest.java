package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import com.example.game_shop.pojo.OrderForm;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/12/17 9:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {
    @Resource
    private OrderService service;

    @Test
    void getOrderList() {
    }

    @Test
    void getOrder() {
    }

    @Test
    void doOrderAdd() {
        OrderForm form = new OrderForm();
        form.setAccount("sheng");
        form.setType("回收");

        form.setLocation("火星");
        form.setPhoneNumber("18877866248");
        form.setName("dolphin");

        List<Map<String, Object>> games = new ArrayList<>();

        for (long[] data : new long[][]{{1, 2}, {3, 4}, {5, 6}}) {
            Map<String, Object> game = new HashMap<>();
            game.put("id", data[0]);
            game.put("num", data[1]);
            games.add(game);
        }
        form.setGames(games);
        Result<String> result = service.doOrderAdd(form);
        System.out.println(result);
        assert result.getCode() == 200;
    }

    @Test
    void doReceiverInfoUpdate() {
    }
}