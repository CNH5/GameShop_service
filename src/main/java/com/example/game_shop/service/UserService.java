package com.example.game_shop.service;

import com.example.game_shop.mapper.UserMapper;
import com.example.game_shop.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 21:29
 */
@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    /**
     * 检验用户是否存在
     *
     * @return 不存在返回true，否则返回false
     */
    public boolean noUser(String account) {
        return userMapper.getUserByAccount(account) == null;
    }

    /**
     * 检验用户名是否存在
     *
     * @return 存在返回true，否则返回false
     */
    public boolean hasName(String name) {
        return userMapper.getName(name) != null;
    }

    /**
     * 获取账号对应的用户
     */
    public User getUser(String account) {
        return userMapper.getUserByAccount(account);
    }

    /**
     * 添加用户
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUser(String account, String password) {
        int n = userMapper.insertUser(account, password, account);
        System.out.println("add user:" + n);
    }


    /**
     * 获取用户的密码
     */
    public String getPassword(String account) {
        return userMapper.getPassword(account);
    }

    /**
     * 修改用户的信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateInfo(User user) {
        int n = userMapper.updateUser(user);
        System.out.println("update user: " + n);
    }

    /**
     * 查询用户
     *
     * @param user 查询条件
     */
    public List<User> queryUsers(User user) {
        return userMapper.queryUsers(user);
    }
}
