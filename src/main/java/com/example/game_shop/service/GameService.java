package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import com.example.game_shop.mapper.GameMapper;
import com.example.game_shop.mapper.GamePictureMapper;
import com.example.game_shop.mapper.UserMapper;
import com.example.game_shop.pojo.BasicGameInfo;
import com.example.game_shop.pojo.Game;
import com.example.game_shop.utils.ResultUtil;
import com.example.game_shop.utils.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    private TokenUtil tokenUtil;

    @Resource
    private GamePictureMapper pictureMapper;

    /**
     * 游戏是否存在
     *
     * @return 存在返回true，否则返回false
     */
    public boolean hasGame(long id) {
        return gameMapper.getGameById(id) != null;
    }

    /**
     * 获取游戏的详细信息
     */
    public Result<Game> getGameById(long id) {
        return ResultUtil.success(gameMapper.getGameById(id));
    }

    /**
     * 查询游戏
     *
     * @param name     游戏名
     * @param platform 游戏平台
     * @param page     页号
     */
    public Result<List<BasicGameInfo>> queryGame(String name, String platform, int page) {
        List<BasicGameInfo> gameList = gameMapper.queryGame(name, platform);
        // 步长20
        int step = 20;
        return ResultUtil.success(gameList.subList((page - 1) * step, (page * step)));
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> updateGameInfo(Game game, HttpServletRequest request) {
        if ("管理员".equals(tokenUtil.getIdent(request))) {
            int updated = gameMapper.updateGameInfo(game);
            int deleted = pictureMapper.deletePicture(game.getId());
            int inserted = pictureMapper.addPicture(game.getId(), game.getImages());

            System.out.println("update game: " + updated);
            System.out.println("delete picture: " + deleted);
            System.out.println("insert picture: " + inserted);

            return ResultUtil.success("修改成功", null);
        } else {
            return ResultUtil.fail("权限不足");
        }
    }
}
