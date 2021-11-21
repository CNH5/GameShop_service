package com.example.game_shop.controller;

import com.example.game_shop.pojo.BasicOrder;
import com.example.game_shop.pojo.Order;
import com.example.game_shop.service.OrderService;
import com.example.game_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 23:45
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @GetMapping("/{account}/order/list")
    public List<BasicOrder> getOrderList(@PathVariable("account") String account, @RequestParam("type") String type) {
        return orderService.getOrderList(account, type);
    }

    @GetMapping("/{account}/order/info")
    public Order getOrder(@PathVariable("account") String account, @RequestParam("id") long id) {
        //加一个账号项，做验证
        return orderService.getOrder(account, id);
    }

    @ResponseBody
    @PostMapping("/{account}/order/add")
    public String addOrder(@PathVariable("account") String account, @RequestBody Order order) {
        if (userService.noUser(account)) {
            return "用户不存在";

        } else {
            orderService.addOrder(account, order);
            return "添加成功";
        }
    }

    @ResponseBody
    @PostMapping("/{account}/order/receiver/update")
    public String updateReceiverInfo(@PathVariable("account") String account, @RequestBody Order order) {
        if (orderService.hasOrder(account, order.getId())) {
            orderService.updateReceiverInfo(account, order);
            return "修改成功";

        } else {
            return "订单不存在";
        }
    }
}
