package com.example.game_shop.controller;

import com.example.game_shop.Result.Result;
import com.example.game_shop.pojo.RecyclePackGame;
import com.example.game_shop.service.RecyclePackService;
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
@RequestMapping("/recycle_pack")
public class RecyclePackController {
    @Resource
    private RecyclePackService recyclePackService;


    @GetMapping("/{account}")
    public Result<List<RecyclePackGame>> getGames(@PathVariable String account,
                                                  @RequestParam("type") String type) {
        try {
            return recyclePackService.getGames(account, type);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/{account}/{type}/delete")
    public Result<String> deleteGame(@PathVariable("account") String account,
                                     @PathVariable("type") String type,
                                     @RequestBody List<Long> idList) {
        try {
            return recyclePackService.deleteGames(account, type, idList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/{account}/{type}/update")
    public Result<String> updateNum(@PathVariable("account") String account,
                                    @PathVariable("type") String type,
                                    @RequestBody List<Map<String, Object>> numList) {
        try {
            return recyclePackService.updateNum(account, type, numList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/{account}/add")
    public Result<String> addGame(@PathVariable("account") String account,
                                  @RequestParam("id") long id,
                                  @RequestParam("type") String type) {
        try {
            return recyclePackService.addGame(account, id, type);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }
}
