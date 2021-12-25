package com.example.game_shop.controller;

import com.example.game_shop.Result.Result;
import com.example.game_shop.pojo.BasicOrder;
import com.example.game_shop.pojo.Order;
import com.example.game_shop.pojo.OrderForm;
import com.example.game_shop.service.OrderService;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 23:45
 */
@RestController
@CrossOrigin
@RequestMapping("/user/order")
public class OrderController {
    @Resource
    private OrderService orderService;


    @GetMapping("/list")
    public Result<List<BasicOrder>> getOrderList(@RequestParam("account") String account,
                                                 @RequestParam("shipped") String shipped,
                                                 @RequestParam("type") String type) {
        try {
            return orderService.getOrderList(account, shipped, type);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求出错，请稍后重试");
        }
    }

    @GetMapping("/info")
    public Result<Order> getOrder(@RequestParam("account") String account,
                                  @RequestParam("id") long id) {
        //加一个账号项，做验证
        try {
            return orderService.getOrder(account, id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求出错，请稍后重试");
        }
    }


    @PostMapping("/add")
    public Result<String> addOrder(@RequestBody OrderForm form) {
        System.out.println(form);
//        try {
//            return orderService.doOrderAdd(form);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResultUtil.error("请求出错，请稍后重试");
//        }
        return ResultUtil.fail("test");
    }


    @PostMapping("/update")
    public Result<Integer> updateReceiverInfo(@RequestBody OrderForm form) {
        try {
            return orderService.doReceiverInfoUpdate(form);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求出错，请稍后重试");
        }
    }
}
