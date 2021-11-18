package com.example.game_shop.mapper;

import com.example.game_shop.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author sheng
 * @date 2021/11/17 8:48
 */
@Mapper
public interface UserMapper {
    @Insert("""
            insert into
            user(account, password, name)
            VALUE (#{account}, #{password}, #{name})
            """)
    void insertUser(User user);

    @Select("""
            select account, name, avatar, gender, regdate
            from user
            where account=#{account}
            """)
    User getUserByAccount(String account);

    @Select("""
            select password
            from user
            where account=#{account}
            """)
    String getPassword(String account);

    @Select("""
            <script>
                select account, name, avatar, gender, regdate
                from user
                <where>
                    <if test="account != null">
                        and account like concat('%', #{account}, '%'),
                    </if>
                    <if test="name != null">
                        and name like concat('%', #{name}, '%'),
                    </if>
                    <if test="gender != null">
                        and gender=#{gender},
                    </if>
                </where>
            </script>
            """)
    List<User> queryUser(User user);

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
    void updateUser(User user);
}
