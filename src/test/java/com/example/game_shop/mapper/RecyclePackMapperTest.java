package com.example.game_shop.mapper;

import com.example.game_shop.GameShopApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sheng
 * @date 2021/12/17 3:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RecyclePackMapperTest {

    @Resource
    RecyclePackMapper mapper;

    @Test
    void getGames() {
    }

    @Test
    void hasGame() {
    }

    @Test
    void deleteGames() {
    }

    @Test
    void updateNum() {
    }

    @Test
    void selectedAll() {
    }

    @Test
    void change() {
    }

    @Test
    void addGame() {
        assert mapper.addGame("sheng", 1, 1, "回收") == 1;
    }

    @Test
    void query() {
    }

    @Test
    void numPlus() {
    }
}