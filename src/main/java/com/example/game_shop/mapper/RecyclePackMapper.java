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
                   stock,
                   selected
            from recycle_pack rp
                     join game g on rp.gid = g.id
            where account = #{account}
              and type = #{type}
            """)
    List<RecyclePackGame> getGames(String account, String type);

    @Delete("""
            <script>
                delete
                from recycle_pack
                where account = #{account}
                  and type = #{type}
                  <foreach collection="list" item="id" separator="or" open="and">
                      gid = #{id}
                  </foreach>
            </script>
            """)
    int deleteGames(@Param("account") String account, @Param("type") String type, @Param("list") List<Long> idList);

    @Update("""
            <script>
                update recycle_pack
                <trim prefix="set" suffixOverrides=",">
                    <foreach collection="numList" item="game" open="num=case" close="else num end,">
                        when gid=#{game.id} then #{game.num}
                    </foreach>
                </trim>
                where account = #{account}
                  and type = #{type}
            </script>
            """)
    int updateNum(@Param("account") String account, @Param("type") String type, @Param("numList") List<Map<String, Object>> numList);

    @Update("""
            update recycle_pack
            set selected=#{selected}
            where account = #{account}
              and type = #{type}
            """)
    int selectedAll(@Param("account") String account, @Param("type") String type, @Param("selected") boolean selected);

    @Update("""
            <script>
                update recycle_pack
                set selected= not selected
                <where>
                    <foreach collection="idList" item="id">
                        or gid = #{id}
                    </foreach>
                    and account = #{account}
                    and type = #{type}
                </where>
            </script>
            """)
    int change(@Param("account") String account, @Param("type") String type, @Param("idList") List<Long> idList);


    @Insert("""
            insert into recycle_pack(account, gid, num, type)
            values (#{account}, #{gid}, #{num}, #{type})
            """)
    int addGame(String account, long gid, int num, String type);

    @Select("""
            select gid
            from recycle_pack
            where account = #{account}
              and gid = #{gid}
              and type = #{type}
            """)
    String query(String account, long gid, String type);

    @Update("""
            update recycle_pack
            set num = num + #{num}
            where account = #{account}
              and gid = #{gid}
              and type = #{type}
            """)
    int numPlus(String account, long gid, int num, String type);
}
