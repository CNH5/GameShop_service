package com.example.game_shop.mapper;

import com.example.game_shop.pojo.BasicGameInfo;
import com.example.game_shop.pojo.Game;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author sheng
 * @date 2021/11/17 9:15
 */
@Mapper
public interface GameMapper {
    @Insert("""
            insert into game(name, platform, stock, price)
            values (#{name}, #{platform}, #{stock}, #{price})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addGame(Game game);

    @Results(id = "game", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "platform", column = "platform"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "images", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.GamePictureMapper.getPicturesByGameId")),
            @Result(property = "history_price", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.HistoryPriceMapper.getHistoryPriceById")),
    })
    @Select("""
            select *
            from game
            where id = #{id}
            """)
    Game getGameById(long id);

    @Select("""
            select id, name, price, cover_image
            from game
            where platform=#{platform}
              and status='正常'
            """)
    //这个非常有可能用不到，和下面的查询游戏完全一致。
    List<BasicGameInfo> getGameList(String platform);

    @Update("""
            <script>
                update game
                <set>
                    <if test="name != ''">name=#{name}</if>
                    <if test="price > 0">price=#{price}</if>
                    <if test="status != null">status=#{status}</if>
                </set>
                where id=#{id}
            </script>
            """)
    void updateGameInfo(Game game);

    @Select("""
            <script>
                select id, name, price, cover_image
                from game
                <where>
                    <if test="name != null">and name like concat('%', #{name}, '%')</if>
                    <if test="platform != null">and platform=#{platform}</if>
                    <if test="true">and status='正常'</if>
                </where>
            </script>
            """)
    List<BasicGameInfo> queryGame(String name, String platform);

    @Select("""
            select price
            from game
            where id=#{gid}
            """)
    double getPrice(long gid);
}
