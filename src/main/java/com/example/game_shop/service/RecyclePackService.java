package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import com.example.game_shop.mapper.RecyclePackMapper;
import com.example.game_shop.pojo.RecyclePackGame;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


    public Result<List<RecyclePackGame>> getGames(String account, String type) {
        return ResultUtil.success(packMapper.getGames(account, type));
    }

    public Result<Integer> deleteGames(String account, String type, List<Long> idList) {
        int deleted = packMapper.deleteGames(account, type, idList);
        return ResultUtil.success("删除成功", deleted);
    }


    public Result<Integer> updateNum(String account, String type, List<Map<String, Object>> numList) {
        int deleted = packMapper.updateNum(account, type, numList);
        return ResultUtil.success("修改成功", deleted);
    }

    public Result<Integer> selectedAll(String account, String type, boolean selected) {
        int updated = packMapper.selectedAll(account, type, selected);
        return ResultUtil.success("修改成功", updated);
    }

    public Result<Integer> change(String account, String type, List<Long> idList) {
        int updated = packMapper.change(account, type, idList);
        return ResultUtil.success("修改成功", updated);
    }


    public Result<String> addGame(String account, long gid, int num, String type) {
        if (packMapper.query(account, gid, type) == null) {
            assert packMapper.addGame(account, gid, num, type) == 1;
            return ResultUtil.success("新增成功", "");
        } else {
            assert packMapper.numPlus(account, gid,num, type) == 1;
            return ResultUtil.success("+1成功", "");
        }
    }
}
