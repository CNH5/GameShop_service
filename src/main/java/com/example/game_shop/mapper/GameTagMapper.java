package com.example.game_shop.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author sheng
 * @date 2021/11/17 23:19
 */
@Mapper
public interface GameTagMapper {
    @Select("""
            select tag
            from game_tag
            where gid=#{gid}
            order by `index`
            """)
    List<String> getTagsByGameId(long gid);

    @Delete("""
            delete from game_tag
            where gid = #{gid}
            """)
    int deleteTag(long gid);

    @Insert("""
            <script>
                insert into game_tag(gid, `index`, tag)
                <foreach collection="tags" item="tag" index="index" open="values" separator=",">
                    (#{gid}, #{index}, #{tag})
                </foreach>
            </script>
            """)
    int insertTags(@Param("gid") long gid, @Param("tags") List<String> tags);
}
