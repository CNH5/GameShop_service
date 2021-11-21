package com.example.game_shop.mapper;

import com.example.game_shop.pojo.RecyclePackGame;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/18 0:01
 */
@Mapper
public interface RecyclePackMapper {

    @Select("""
            select id,
                   num,
                   name,
                   g.price  as now_price,
                   rp.price as old_price,
                   cover_image,
                   platform,
                   status,
                   stock
            from recycle_pack rp
                     join game g on rp.gid = g.id
            where account = #{account}
              and type = #{type}
            """)
    List<RecyclePackGame> getGames(String account, String type);

    @Select("""
            select count(*) > 0
            from recycle_pack
            where account = #{account}
              and gid = #{id}
              and type = #{type}
            """)
    boolean hasGame(String account, long id, String type);

    @Delete("""
            <script>
                delete
                from recycle_pack
                where account = #{account}
                  and type = #{type}
                <trim prefix="and" suffixOverrides="or">
                    <foreach collection="list" item="id" separator="or">
                        gid = #{id}
                    </foreach>
                </trim>
            </script>
            """)
    int deleteGames(@Param("account") String account, @Param("type") String type, @Param("list") List<Long> idList);

    @Update("""
            <script>
                update recycle_pack
                <trim prefix="set" suffixOverrides=",">
                    <trim prefix="num=case" suffix="end,">
                        <foreach collection="numList" item="game">
                            when gid=#{game.id} then #{game.num}
                        </foreach>
                     </trim>
                </trim>
                where account = #{account}
                  and type = #{type}
            </script>
            """)
    int updateNum(@Param("account") String account, @Param("type") String type, @Param("numList") List<Map<String, Object>> numList);

    @Insert("""
            insert into recycle_pack(account, gid, price, type)
            values (#{account}, #{gid}, #{price}, #{type})
            """)
    int addGame(String account, long gid, double price, String type);
}
