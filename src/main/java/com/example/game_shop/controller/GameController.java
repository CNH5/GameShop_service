package com.example.game_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.game_shop.Result.Result;
import com.example.game_shop.annotation.DoWithoutToken;
import com.example.game_shop.pojo.BasicGameInfo;
import com.example.game_shop.pojo.Game;
import com.example.game_shop.service.GameService;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/18 23:45
 */
@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {
    @Resource
    private GameService gameService;


    @DoWithoutToken
    @GetMapping("/query")
    public Result<List<BasicGameInfo>> queryGame(@RequestParam(value = "name", defaultValue = "") String name,
                                                 @RequestParam("platform") String platform,
                                                 @RequestParam(value = "page", defaultValue = "1") int page) {
        try {
            return gameService.queryGames(name, platform, page);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }

    @DoWithoutToken
    @GetMapping("/list")
    public Result<List<Map<String, Object>>> getGames(@RequestParam("id") String idListString) {
        try {
            return gameService.getGames(JSONObject.parseObject(idListString, new TypeReference<>() {
            }));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }

    @DoWithoutToken
    @GetMapping("/info/{id}")
    public Result<Game> getGame(@PathVariable("id") long id) {
        try {
            return gameService.getGameById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }
}
