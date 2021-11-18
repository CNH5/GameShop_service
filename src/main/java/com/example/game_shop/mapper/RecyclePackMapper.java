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
            """)
    List<RecyclePackGame> getGamesByAccount(long account);

    @Delete("""
            <script>
                delete
                from recycle_pack
                where account=#{account}
                <trim prefix="and" suffixOverrides="or">
                    <foreach collection="list" item="id" separator="or">
                        gid=#{id}
                    </foreach>
                </trim>
            </script>
            """)
    void deleteGame(Map<String, Object> deleteForm);

    @Update("""
            <script>
                update recycle_pack
                <trim prefix="set" suffixOverrides=",">
                    <trim prefix="num=case" suffix="end,">
                        <foreach collection="list" item="game">
                            when id=#{game.id} then #{game.num}
                        </foreach>
                     </trim>
                </trim>
                where account=#{account}
            </script>
            """)
    void updateNum(Map<String, Object> updateForm);

    @Insert("""
            insert into recycle_pack(account, gid, price)
            values (#{account}, #{gid}, #{price})
            """)
    void addGame(String account, long gid, double price);
}
