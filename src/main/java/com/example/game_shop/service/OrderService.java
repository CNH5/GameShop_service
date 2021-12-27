package com.example.game_shop.service;

import com.example.game_shop.Result.Result;
import com.example.game_shop.mapper.GameMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.game_shop.config.ConstParam.phoneNumber;

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

    @Resource
    private GameMapper gameMapper;


    public Result<List<BasicOrder>> getOrderList(String account, boolean shipped, String type) {
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
            // 无错误信息，校验要购买的游戏是否全部都能购买
            List<Long> idList = new ArrayList<>();
            for (Map<String, String> data : form.getGames()) {
                idList.add(Long.parseLong(data.get("id")));
            }
            if (gameMapper.hasN(idList) != form.getGames().size()) {
                return ResultUtil.fail("商品序列不正确");
            }
            // 插入订单
            int insertOrder = orderMapper.insert(form);
            assert insertOrder == 1;

            // 插入订单-商品
            int insertGame = orderGameMapper.insertGame(form);
            assert insertGame == form.getGames().size();

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
        System.out.println(form);
        if (!StringUtils.hasLength(form.getType()) ||
                !List.of("回收", "购买").contains(form.getType())) {
            return "订单类型不正确";
        }
        if ("购买".equals(form.getType())) {
            if (!StringUtils.hasLength(form.getName())) {
                return "收货人姓名不能为空";

            } else if (!StringUtils.hasLength(form.getLocation())) {
                return "收货地址不能为空";

            } else if (!StringUtils.hasLength(form.getPhoneNumber())) {
                return "电话号码不能为空";

            } else if (!phoneNumber.matcher(form.getPhoneNumber()).matches()) {
                return "电话号码不正确";
            }
        }

        return null;
    }
}
