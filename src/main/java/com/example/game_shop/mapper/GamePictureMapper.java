package com.example.game_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sheng
 * @date 2021/11/17 23:15
 */
@Mapper
public interface GamePictureMapper {
    @Select("""
            select url
            from game_picture
            where gid=#{id}
            """)
    List<String> getPicturesByGameId(long gid);
}
