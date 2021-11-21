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
    int insertGame(Game game);

    @Results(id = "game", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "platform", column = "platform"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "status", column = "status"),
            @Result(property = "images", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.GamePictureMapper.getPicturesByGameId")),
            @Result(property = "history_price", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.HistoryPriceMapper.getHistoryPriceById")),
    })
    @Select("""
            select id,
                   name,
                   platform,
                   stock,
                   price,
                   status
            from game
            where id = #{id}
            """)
    Game getGameById(long id);

    @Select("""
            <script>
                select count(*)
                from game
                <foreach collection="idList" item="id" open="where" separator="or">
                    id=#{id}
                </foreach>
            </script>
            """)
    int hasN(List<Integer> idList);

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
    int updateGameInfo(Game game);

    @Select("""
            <script>
                select id, name, price, cover_image
                from game
                <where>
                    <if test="name != ''">and name like concat('%', #{name}, '%')</if>
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
