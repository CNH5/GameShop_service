package com.example.game_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.game_shop.Result.Result;
import com.example.game_shop.pojo.RecyclePackGame;
import com.example.game_shop.service.RecyclePackService;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 23:45
 */
@RestController
@CrossOrigin
@RequestMapping("/pack")
public class RecyclePackController {
    @Resource
    private RecyclePackService PackService;


    @GetMapping("/list")
    public Result<List<RecyclePackGame>> getGames(@RequestParam("account") String account,
                                                  @RequestParam("type") String type) {
        try {
            return PackService.getGames(account, type);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/delete")
    public Result<Integer> deleteGame(@RequestParam("account") String account,
                                      @RequestParam("type") String type,
                                      @RequestParam("idList") String idListString) {
        try {
            return PackService.deleteGames(account, type, JSONObject.parseArray(idListString, Long.class));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }

    @PostMapping("/selected/all")
    public Result<Integer> selectAll(@RequestParam("account") String account,
                                     @RequestParam("type") String type,
                                     @RequestParam("selected") boolean selected) {
        try {
            return PackService.selectedAll(account, type, selected);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }

    @PostMapping("/selected")
    public Result<Integer> selected(@RequestParam("account") String account,
                                  @RequestParam("type") String type,
                                  @RequestParam("idList") String idListString) {
        try {
            return PackService.selected(account, type, JSONObject.parseArray(idListString, Long.class));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/update")
    public Result<Integer> updateNum(@RequestParam("account") String account,
                                     @RequestParam("type") String type,
                                     @RequestParam("numList") String numListString) {
        try {
            return PackService.updateNum(
                    account,
                    type,
                    JSONObject.parseObject(numListString, new TypeReference<>() {
                    })
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/add")
    public Result<String> addGame(@RequestParam("account") String account,
                                  @RequestParam("id") long id,
                                  @RequestParam("num") int num,
                                  @RequestParam("type") String type) {
        try {
            return PackService.addGame(account, id, num, type);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }
}
