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


    @GetMapping("/list")
    public Result<List<RecyclePackGame>> getGames(@RequestParam("account") String account,
                                                  @RequestParam("type") String type) {
        try {
            return recyclePackService.getGames(account, type);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/delete")
    public Result<Integer> deleteGame(@RequestParam("account") String account,
                                      @RequestParam("type") String type,
                                      @RequestParam("idList") List<Long> idList) {
        try {
            return recyclePackService.deleteGames(account, type, idList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }

    @PostMapping("/select/all")
    public Result<Integer> selectAll(@RequestParam("account") String account,
                                     @RequestParam("type") String type,
                                     @RequestParam("selected") boolean selected) {
        try {
            return recyclePackService.selectedAll(account, type, selected);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }

    @PostMapping("/select")
    public Result<Integer> change(@RequestParam("account") String account,
                                  @RequestParam("type") String type,
                                  @RequestParam("idList") List<Long> idList) {
        try {
            return recyclePackService.change(account, type, idList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }


    @PostMapping("/update")
    public Result<Integer> updateNum(@RequestParam("account") String account,
                                     @RequestParam("type") String type,
                                     @RequestParam("numList") List<Map<String, Object>> numList) {
        try {
            return recyclePackService.updateNum(account, type, numList);
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
            return recyclePackService.addGame(account, id, num, type);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求失败,请稍后重试");
        }
    }
}
