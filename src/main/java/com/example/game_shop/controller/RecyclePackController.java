package com.example.game_shop.controller;

import com.example.game_shop.pojo.RecyclePackGame;
import com.example.game_shop.service.RecyclePackService;
import com.example.game_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/18 23:45
 */
@Controller
@CrossOrigin
@RequestMapping("/recycle_pack")
public class RecyclePackController {
    @Resource
    private RecyclePackService recyclePackService;

    @Resource
    private UserService userService;


    @GetMapping("/{account}")
    public List<RecyclePackGame> getGames(@PathVariable String account, @RequestParam("type") String type) {
        return recyclePackService.getGames(account, type);
    }

    @ResponseBody
    @PostMapping("/{account}/{type}/delete")
    public String deleteGame(@PathVariable("account") String account,
                             @PathVariable("type") String type, @RequestBody List<Long> idList) {
        if (userService.noUser(account)) {
            return "用户不存在";

        } else {
            recyclePackService.deleteGames(account, type, idList);
            return "删除成功";
        }
    }

    @ResponseBody
    @PostMapping("/{account}/{type}/update")
    public String updateNum(@PathVariable("account") String account,
                            @PathVariable("type") String type, @RequestBody List<Map<String, Object>> numList) {
        if (userService.noUser(account)) {
            return "用户不存在";

        } else {
            recyclePackService.updateNum(account, type, numList);
            return "修改成功";
        }
    }

    @ResponseBody
    @PostMapping("/{account}/add")
    public String addGame(@PathVariable("account") String account,
                          @RequestParam("id") long id, @RequestParam("type") String type) {
        if (userService.noUser(account)) {
            return "用户不存在";

        } else if (recyclePackService.hasGame(account, id, type)) {
            return "已在回收袋中";

        } else {
            recyclePackService.addGame(account, id, type);
            return "加入成功";
        }
    }
}
