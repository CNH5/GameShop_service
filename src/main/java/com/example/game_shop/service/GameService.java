package com.example.game_shop.service;

import com.example.game_shop.mapper.GameMapper;
import com.example.game_shop.mapper.GamePictureMapper;
import com.example.game_shop.pojo.BasicGameInfo;
import com.example.game_shop.pojo.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 21:49
 */
@Service
public class GameService {
    @Resource
    GameMapper gameMapper;

    @Resource
    GamePictureMapper pictureMapper;

    /**
     * 游戏是否存在
     *
     * @return 存在返回true，否则返回false
     */
    public boolean hasGame(long id) {
        return gameMapper.getGameById(id) != null;
    }

    public Game getGameById(long id) {
        return gameMapper.getGameById(id);
    }

    public List<BasicGameInfo> queryGame(String name, String platform) {
        return gameMapper.queryGame(name, platform);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateGameInfo(Game game) {
        int updated = gameMapper.updateGameInfo(game);
        int deleted = pictureMapper.deletePicture(game.getId());
        int inserted = pictureMapper.addPicture(game.getId(), game.getImages());

        System.out.println("update game: " + updated);
        System.out.println("delete picture: " + deleted);
        System.out.println("insert picture: " + inserted);
    }


}
