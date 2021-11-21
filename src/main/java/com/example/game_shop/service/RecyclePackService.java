package com.example.game_shop.service;

import com.example.game_shop.mapper.GameMapper;
import com.example.game_shop.mapper.RecyclePackMapper;
import com.example.game_shop.pojo.RecyclePackGame;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    RecyclePackMapper packMapper;

    @Resource
    GameMapper gameMapper;

    public boolean hasGame(String account, long id, String type) {
        return packMapper.hasGame(account, id, type);
    }

    public List<RecyclePackGame> getGames(String account, String type) {
        return packMapper.getGames(account, type);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteGames(String account, String type, List<Long> idList) {
        int deleteNum = packMapper.deleteGames(account, type, idList);
        System.out.println("delete num:" + deleteNum);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateNum(String account, String type, List<Map<String, Object>> numList) {
        int updated = packMapper.updateNum(account, type, numList);
        System.out.println("update recycle_pack_game: " + updated);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addGame(String account, long gid, String type) {
        int gameAdded = packMapper.addGame(account, gid, gameMapper.getPrice(gid), type);
        System.out.println("add num:" + gameAdded);
    }
}
