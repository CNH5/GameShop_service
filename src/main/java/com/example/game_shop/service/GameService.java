package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import com.example.game_shop.mapper.GameMapper;
import com.example.game_shop.mapper.GamePictureMapper;
import com.example.game_shop.pojo.BasicGameInfo;
import com.example.game_shop.pojo.Game;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 21:49
 */
@Service
public class GameService {
    @Resource
    private GameMapper gameMapper;

    @Resource
    private GamePictureMapper pictureMapper;


    /**
     * 获取游戏的详细信息
     */
    public Result<Game> getGameById(long id) {
        Game game = gameMapper.getGameById(id);
        return game != null ? ResultUtil.success(game) : ResultUtil.fail("游戏不存在");
    }


    /**
     * 查询游戏
     *
     * @param name     游戏名
     * @param platform 游戏平台
     * @param page     页号
     */
    public Result<List<BasicGameInfo>> queryGame(String name, String platform, int page) {
        if (StringUtils.hasLength(platform) && List.of("NS", "PS").contains(platform)) {
            List<BasicGameInfo> gameList = gameMapper.queryGame(name, platform);
            // 步长20
            int step = 20;
            int startIndex = Math.min((page - 1) * step, gameList.size());
            int endIndex = Math.min(page * step, gameList.size());

            if (endIndex > startIndex) {
                return ResultUtil.success(gameList.subList(startIndex, endIndex));
            } else {
                return ResultUtil.fail("暂无更多数据");
            }

        } else {
            return ResultUtil.fail("游戏所在平台不正确");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> updateGameInfo(Game game) {
        int updated = gameMapper.updateGameInfo(game);
        int deleted = pictureMapper.deletePicture(game.getId());
        int inserted = pictureMapper.addPicture(game.getId(), game.getImages());

        System.out.println("update game: " + updated);
        System.out.println("delete picture: " + deleted);
        System.out.println("insert picture: " + inserted);

        return ResultUtil.success("修改成功", null);
    }
}
