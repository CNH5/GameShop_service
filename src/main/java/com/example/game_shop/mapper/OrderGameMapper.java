package com.example.game_shop.mapper;

import com.example.game_shop.pojo.OrderForm;
import com.example.game_shop.pojo.OrderGame;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sheng
 * @date 2021/11/17 23:45
 */
@Mapper
public interface OrderGameMapper {
    @Select("""
            select id,
                   name,
                   num,
                   cover_image,
                   og.price  as price,
                   og.status as status
            from order_game og
                     join game g on og.gid = g.id
            where oid = #{oid}
            """)
    List<OrderGame> getOrderGame(long oid);


    @Insert("""
            <script>
                insert into order_game(oid, gid, num)
                <foreach collection="games" item="game" open="values" separator=",">
                    (#{id}, #{game.id}, #{game.num})
                </foreach>
            </script>
            """)
    int insertGame(OrderForm form);
}
