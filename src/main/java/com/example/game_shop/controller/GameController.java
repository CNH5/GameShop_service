package com.example.game_shop.controller;

import com.example.game_shop.pojo.BasicGameInfo;
import com.example.game_shop.pojo.Game;
import com.example.game_shop.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 23:45
 */
@Controller
@CrossOrigin
@RequestMapping("/game")
public class GameController {
    @Resource
    private GameService gameService;

    @GetMapping("/info/list")
    public List<BasicGameInfo> getGameList(@RequestParam(value = "account", defaultValue = "") String name,
                                           @RequestParam("platform") String platform,
                                           @RequestParam("page") int page) {
        //TODO:校验输入
        List<BasicGameInfo> gameList = gameService.queryGame(name, platform);
        return gameList.subList(page * 20, (page + 1) * 20);
    }

    @GetMapping("{id}/info")
    public Game getGame(@PathVariable long id) {
        //查询结果为空，就是找不到嘛..还需要做什么处理吗..
        return gameService.getGameById(id);
    }
}
