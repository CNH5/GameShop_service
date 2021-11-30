package com.example.game_shop.service;

import com.example.game_shop.GameShopApplication;
import com.example.game_shop.pojo.RecyclePackGame;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author sheng
 * @date 2021/11/19 9:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GameShopApplication.class)
class RecyclePackServiceTest {
    @Resource
    private RecyclePackService recyclePackService;

    @Test
    void hasGame() {
        System.out.println(recyclePackService.hasGame("sheng", 1, "回收"));
    }

    @Test
    void getGames() {
        for (RecyclePackGame game : recyclePackService.getGames("sheng", "回收", null).getData()) {
            System.out.println(game);
        }
    }

    @Test
    void deleteGames() {
        recyclePackService.deleteGames("sheng", "回收", List.of(1L, 5L), null);
    }

    @Test
    void updateNum() {
        List<Map<String, Object>> numList = new ArrayList<>();
        Map<String, Object> num = new HashMap<>();
        num.put("num", 2);
        num.put("id", 1);
        numList.add(num);

        Map<String, Object> num2 = new HashMap<>();
        num2.put("num", 4);
        num2.put("id", 5);
        numList.add(num2);

        recyclePackService.updateNum("sheng", "回收", numList, null);
    }

    @Test
    void addGame() {
        recyclePackService.addGame("sheng", 1, "回收", null);
    }
}