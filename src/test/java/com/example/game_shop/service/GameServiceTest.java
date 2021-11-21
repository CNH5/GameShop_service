package com.example.game_shop.service;

import com.example.game_shop.GameShopApplication;
import com.example.game_shop.pojo.BasicGameInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author sheng
 * @date 2021/11/19 9:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GameShopApplication.class)
class GameServiceTest {
    @Resource
    private GameService gameService;

    @Test
    void hasGame() {
        System.out.println(gameService.hasGame(1));
    }

    @Test
    void getGameById() {
        System.out.println(gameService.getGameById(1));
    }

    @Test
    void queryGame() {
        for (BasicGameInfo g: gameService.queryGame("塞尔达","NS")){
            System.out.println(g);
        }
    }

    @Test
    void updateGameInfo() {
    }
}