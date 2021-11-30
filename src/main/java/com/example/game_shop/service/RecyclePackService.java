package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import com.example.game_shop.mapper.GameMapper;
import com.example.game_shop.mapper.RecyclePackMapper;
import com.example.game_shop.pojo.RecyclePackGame;
import com.example.game_shop.utils.ResultUtil;
import com.example.game_shop.utils.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/18 22:06
 */
@Service
public class RecyclePackService {
    @Resource
    private RecyclePackMapper packMapper;

    @Resource
    private GameMapper gameMapper;

    @Resource
    private TokenUtil tokenUtil;

    public boolean hasGame(String account, long id, String type) {
        return packMapper.hasGame(account, id, type);
    }


    public Result<List<RecyclePackGame>> getGames(String account, String type, HttpServletRequest request) {
        if (tokenUtil.check(account, request)) {
            return ResultUtil.success(packMapper.getGames(account, type));
        } else {
            return ResultUtil.fail("用户不一致");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> deleteGames(String account, String type, List<Long> idList, HttpServletRequest request) {
        if (tokenUtil.check(account, request)) {
            int deleted = packMapper.deleteGames(account, type, idList);
            System.out.println("delete num:" + deleted);
            return ResultUtil.success("删除成功", null);
        } else {
            return ResultUtil.fail("用户不一致");
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public Result<String> updateNum(String account, String type, List<Map<String, Object>> numList, HttpServletRequest request) {
        if (tokenUtil.check(account, request)) {
            int updated = packMapper.updateNum(account, type, numList);
            System.out.println("update recycle_pack_game: " + updated);
            return ResultUtil.success("删除成功", null);
        } else {
            return ResultUtil.fail("用户不一致");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> addGame(String account, long gid, String type, HttpServletRequest request) {
        if (tokenUtil.check(account, request)) {
            int added = packMapper.addGame(account, gid, gameMapper.getPrice(gid), type);
            System.out.println("add num:" + added);
            return ResultUtil.success("删除成功", String.valueOf(added));
        } else {
            return ResultUtil.fail("用户不一致");
        }
    }
}
