package com.example.game_shop.mapper;

import com.example.game_shop.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/17 8:48
 */
@Mapper
public interface UserMapper {
    @Insert("""
            insert into
            user(account, password, name)
            value (#{account}, #{password}, #{name})
            """)
    void insertUser(String account, String password, String name);

    @Select("""
            select
                account,
                name,
                avatar,
                gender,
                regdate,
                DATE_FORMAT(regdate, '%Y-%m-%d %H:%i:%s') as regdate
            from user
            where account=#{account}
            """)
    User getUserByAccount(String account);

    @Select("""
            select *
            from user
            where account=#{account}
            """)
    Map<String, Object> getUserAll(String account);

    @Select("""
            select name
            from user
            where name=#{name}
            """)
    String getName(String name);

    @Select("""
            select id
            from user
            where account = #{account}
            """)
    String getId(String account);

    @Select("""
            select ident
            from user
            where account=#{account}
            """)
    String getIdent(String account);

    @Select("""
            <script>
                select account, name, avatar, gender, regdate
                from user
                <where>
                    <if test="account != null">
                        and account like concat('%', #{account}, '%')
                    </if>
                    <if test="name != null">
                        and name like concat('%', #{name}, '%')
                    </if>
                    <if test="gender != null">
                        and gender=#{gender}
                    </if>
                </where>
            </script>
            """)
    List<User> queryUsers(User user);

    @Update("""
            <script>
                update user
                <set>
                    <if test="name != null">
                        name = #{name},
                    </if>
                    <if test="avatar != null">
                        avatar = #{avatar},
                    </if>
                    <if test="gender != null">
                        gender = #{gender},
                    </if>
                </set>
                where account=#{account}
            </script>
            """)
    int updateUser(User user);
}
