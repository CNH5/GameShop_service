package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import com.example.game_shop.mapper.UserMapper;
import com.example.game_shop.pojo.User;
import com.example.game_shop.utils.ResultUtil;
import com.example.game_shop.utils.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/18 21:29
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenUtil tokenUtil;

    /**
     * 检验用户是否存在
     *
     * @return 不存在返回true，否则返回false
     */
    public boolean noUser(String account) {
        return userMapper.getUserByAccount(account) != null;
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
     * 获取账号对应的用户信息
     */
    public Result<User> getInfo(String account) {
        return ResultUtil.success(userMapper.getUserByAccount(account));
    }

    public Result<String> doLogin(String account, String password, HttpServletResponse response) {
        if (checkForm(account, password)) {
            Map<String, Object> user = userMapper.getUserAll(account);
            if (user.get("password") == null) {
                // 查询结果为空，用户不存在
                return ResultUtil.fail("用户不存在");
            } else if (user.get("password").equals(password)) {
                // 密码正确,设置返回头包含token
                tokenUtil.setToken(
                        String.valueOf(user.get("account")),
                        String.valueOf(user.get("id")),
                        response);
                return ResultUtil.success("登录成功", null);
            } else {
                // 密码不正确
                return ResultUtil.fail("密码错误");
            }
        } else {
            //表单验证不通过
            return ResultUtil.fail("账号或密码不能为空");
        }
    }

    /**
     * 执行注册功能
     */
    public Result<String> doRegister(String account, String password) {
        if (checkForm(account, password)) {
            if (noUser(account)) {
                userMapper.insertUser(account, password, account);
                // 插入成功
                return ResultUtil.success("注册成功", null);
            } else {
                // 账号已存在，不能继续注册
                return ResultUtil.fail("账号已存在");
            }
        } else {
            //表单验证不通过
            return ResultUtil.fail("账号或密码不能为空");
        }
    }

    /**
     * 执行信息修改功能
     */
    public Result<Integer> doInfoUpdate(User infoForm) {
        // 校验输入
        String msg = checkInfo(infoForm);
        if (msg == null) {
            // 输入无误修改信息
            int updated = userMapper.updateUser(infoForm);
            assert updated == 1;
            return ResultUtil.success("修改成功", updated);
        } else {
            // 输入有误
            return ResultUtil.fail(msg);
        }
    }

    /**
     * 查询用户
     *
     * @param queryForm 查询条件
     */
    public Result<List<User>> doUserQuery(User queryForm) {
        return ResultUtil.success(userMapper.queryUsers(queryForm));
    }

    /**
     * 校验登陆注册表
     *
     * @return 合规返回true，否则返回false
     */
    private boolean checkForm(String account, String password) {
        return StringUtils.hasLength(account.trim()) && StringUtils.hasLength(password.trim());
    }

    /**
     * 校验信息修改表
     *
     * @return 没有错误返回null，否则返回错误信息
     */
    private String checkInfo(User infoForm) {
        if (!StringUtils.hasLength(infoForm.getName().trim())) {
            return "昵称不能为空";
        } else if (!StringUtils.hasLength(infoForm.getGender()) ||
                !List.of("男", "女", "未知").contains(infoForm.getGender())) {
            return "性别填写有误";
        } else if (noUser(infoForm.getAccount())) {
            return "用户不存在";
        } else if (hasName(infoForm.getName())) {
            return "用户名已存在";
        }
        return null;
    }
}
