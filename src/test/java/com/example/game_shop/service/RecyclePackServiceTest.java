package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * @author sheng
 * @date 2021/12/19 9:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class RecyclePackServiceTest {
    @Resource
    private RecyclePackService service;

    @Test
    void getGames() {
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

    // 测试明明是通过的但是为什么在controller中调用会插不进去？
    @Test
    void addGame() {
        Result<String> result = service.addGame("sheng", 1, 2, "购买");
        assert result.getCode() == 200;
        System.out.println(result);
    }
}