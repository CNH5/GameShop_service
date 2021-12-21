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


    public Result<Integer> selected(String account, String type, List<Long> idList) {
        int updated = packMapper.change(account, type, idList);
        return ResultUtil.success("修改成功", updated);
    }


    public Result<String> addGame(String account, long gid, int num, String type) {
        // 很奇怪，断言执行mapper在controller中不能自动提交，但是在测试类里面又可以...
        if (packMapper.query(account, gid, type) == null) {
            int added = packMapper.addGame(account, gid, num, type);
            assert added == 1;

        } else {
            int updated = packMapper.numPlus(account, gid, num, type);
            assert updated == 1;

        }
        return ResultUtil.success("添加成功", "");
    }
}
