package com.example.game_shop.mapper;

import com.example.game_shop.pojo.BasicGameInfo;
import com.example.game_shop.pojo.Game;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/17 9:15
 */
@Mapper
public interface GameMapper {
    @Select("""
            <script>
                select count(*)
                from game
                <foreach collection="idList" item="id" open="where" separator="or">
                    id = #{id}
                </foreach>
                  and status = '正常'
            </script>
            """)
    int hasN(List<Long> idList);

    @Select("""
            select date, price
            from history_price
            where gid=#{gid}
            """)
    List<Map<String, Object>> getHistoryPriceById(long gid);

    @Results(id = "game", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "platform", column = "platform"),
            @Result(property = "stock", column = "stock"),
            @Result(property = "price", column = "price"),
            @Result(property = "status", column = "status"),
            @Result(property = "cover_image", column = "cover_image"),
            @Result(property = "images", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.GamePictureMapper.getPicturesByGameId")),
            @Result(property = "history_price", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.GameMapper.getHistoryPriceById")),
            @Result(property = "tags", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.GameTagMapper.getTagsByGameId")),
    })
    @Select("""
            select id,
                   name,
                   platform,
                   stock,
                   price,
                   status,
                   cover_image
            from game
            where id = #{id}
            """)
    Game getGameById(long id);


    @Select("""
            <script>
                select name,
                       cover_image,
                       price
                from game
                <foreach collection="list" item="id" open="where" separator="or">
                    id = #{id}
                </foreach>
            </script>
            """)
    List<Map<String, Object>> getGames(@Param("list") List<Long> idList);

    @Update("""
            <script>
                update game
                <set>
                    <if test="name != ''">name=#{name},</if>
                    <if test="price > 0">price=#{price},</if>
                    <if test="status != null">status=#{status},</if>
                </set>
                where id = #{id}
            </script>
            """)
    int updateGameInfo(Game game);

    @Select("""
            <script>
                select id, name, price, cover_image
                from game
                <where>
                    <if test="name != null and name != ''">and name like concat('%', #{name}, '%')</if>
                    <if test="platform != null">and platform=#{platform}</if>
                    <if test="true">and status='正常'</if>
                </where>
            </script>
            """)
    List<BasicGameInfo> queryGame(String name, String platform);
}
