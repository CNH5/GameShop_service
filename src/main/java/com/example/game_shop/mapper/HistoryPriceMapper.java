package com.example.game_shop.mapper;

import com.example.game_shop.pojo.Game;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/17 23:19
 */
@Mapper
public interface HistoryPriceMapper {
    @Select("""
            select date, price
            from history_price
            where gid=#{gid}
            """)
    List<Map<String, Object>> getHistoryPriceById(long gid);

    @Insert("""
            insert into history_price(gid, price)
            values (#{id}, #{price})
            """)
    int addRecord(Game game);
}
