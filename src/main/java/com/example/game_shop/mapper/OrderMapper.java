package com.example.game_shop.mapper;

import com.example.game_shop.pojo.BasicOrder;
import com.example.game_shop.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author sheng
 * @date 2021/11/17 23:42
 */
@Mapper
public interface OrderMapper {
    @Results(id = "basic_order", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "date", column = "date"),
            @Result(property = "express_delivery_id", column = "express_delivery_id"),
            @Result(property = "games", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.OrderGameMapper.getOrderGame"))
    })
    @Select("""
            <script>
                select id, date, type, express_delivery_id
                from orders
                <where>
                    <if test="true">and account = #{account}</if>
                    <if test="type != null">and type = #{type}</if>
                </where>
            </script>
            """)
    List<BasicOrder> getOrderList(String account, String type);


    @Results(id = "order", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "date", column = "date"),
            @Result(property = "type", column = "type"),
            @Result(property = "name", column = "name"),
            @Result(property = "location", column = "location"),
            @Result(property = "phoneNumber", column = "phoneNumber"),
            @Result(property = "express_delivery_company", column = "express_delivery_company"),
            @Result(property = "express_delivery_id", column = "express_delivery_id"),
            @Result(property = "games", column = "id",
                    many = @Many(select = "com.example.game_shop.mapper.OrderGameMapper.getOrderGame"))
    })
    @Select("""
            select *
            from orders
            where id=#{id}
            """)
    Order getOrder(String account, long id);


    @Insert("""
            insert into
            orders(account, name, type, location, phoneNumber, express_delivery_company, express_delivery_id)
            values(#{account}, #{order.name}, #{order.type}, #{order.location},
                   #{order.phoneNumber}, #{order.express_delivery_company}, #{order.express_delivery_id})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "order.id")
    int insert(@Param("account") String account, @Param("order") Order order);


    @Update("""
            update orders
            set name=#{order.name},
                location=#{order.location},
                phoneNumber=#{order.phoneNumber}
            where account=#{account} and id=#{order.id}
            """)
    int updateReceiverInfo(@Param("account") String account, @Param("order") Order order);
}
