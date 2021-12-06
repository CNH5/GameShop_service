package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import com.example.game_shop.mapper.OrderGameMapper;
import com.example.game_shop.mapper.OrderMapper;
import com.example.game_shop.pojo.BasicOrder;
import com.example.game_shop.pojo.Order;
import com.example.game_shop.pojo.OrderForm;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author sheng
 * @date 2021/11/18 22:18
 */
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderGameMapper orderGameMapper;

    private static final Pattern phoneNumber = Pattern.compile("^(13[0-9]|14[01456879]|15[0-3,5-9]|16[2567]|17[0-8]|18[0-9]|19[0-3,5-9])d{8}$");

    public Result<List<BasicOrder>> getOrderList(String account, String shipped, String type) {
        return ResultUtil.success(orderMapper.getOrderList(account, shipped, type));
    }

    public Result<Order> getOrder(String account, long id) {
        Order order = orderMapper.getOrder(account, id);
        return order != null ? ResultUtil.success(order) : ResultUtil.fail("订单不存在");
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> doOrderAdd(OrderForm form) {
        // 订单校验
        String msg = checkOrderForm(form);
        if (msg == null) {
            // 无错误信息
            // 插入订单
            assert orderMapper.insert(form) == 1;
            // 插入订单-商品
            assert orderGameMapper.insertGame(form) == form.getGames().size();

            return ResultUtil.success("添加成功", null);
        } else {
            // 有错误信息
            return ResultUtil.fail(msg);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> doReceiverInfoUpdate(OrderForm form) throws Exception {
        int updated = orderMapper.updateReceiverInfo(form);
        System.out.println("update receiver num: " + updated);

        if (updated == 0) {
            return ResultUtil.fail("订单不存在");
        } else if (updated == 1) {
            return ResultUtil.success("修改成功", 1);
        } else {
            throw new Exception("修改数量为" + updated);
        }
    }

    private String checkOrderForm(OrderForm form) {
        if (!StringUtils.hasLength(form.getName().trim())) {
            return "收货人姓名不能为空";

        } else if (!StringUtils.hasLength(form.getLocation().trim())) {
            return "收货地址不能为空";

        } else if (!StringUtils.hasLength(form.getPhoneNumber())) {
            return "电话号码不能为空";

        } else if (!phoneNumber.matcher(form.getPhoneNumber()).matches()) {
            return "电话号码不正确";

        } else if (!StringUtils.hasLength(form.getType()) ||
                !List.of("回收", "购买").contains(form.getType())) {
            return "订单类型不正确";

        }
        return null;
    }
}
