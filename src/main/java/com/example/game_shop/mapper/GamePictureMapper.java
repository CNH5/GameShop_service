package com.example.game_shop.mapper;

import org.apache.ibatis.annotations.*;

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
            where gid=#{gid}
            order by `index`
            """)
    List<String> getPicturesByGameId(long gid);

    @Delete("""
            delete
            from game_picture
            where gid=#{gid}
            """)
    int deletePicture(long gid);

    @Insert("""
            <script>
                insert into game_picture(gid, `index`, url)
                <foreach collection="list" item="url" index="index" open="values" separator=",">
                    (#{gid}, #{index}, #{url})
                </foreach>
            </script>
            """)
    int insertPicture(@Param("gid") long gid, @Param("list") List<String> urls);
}
