package com.example.game_shop.controller;

import com.example.game_shop.Result.Result;
import com.example.game_shop.pojo.BasicOrder;
import com.example.game_shop.pojo.Order;
import com.example.game_shop.pojo.OrderForm;
import com.example.game_shop.service.OrderService;
import com.example.game_shop.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 23:45
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class OrderController {
    @Resource
    private OrderService orderService;


    @GetMapping("/{account}/order/list")
    public Result<List<BasicOrder>> getOrderList(@PathVariable("account") String account,
                                                 @RequestParam("shipped") String shipped,
                                                 @RequestParam("type") String type,
                                                 HttpServletRequest request) {
        try {
            return orderService.getOrderList(account, shipped, type, request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求出错，请稍后重试");
        }
    }

    @GetMapping("/{account}/order/info")
    public Result<Order> getOrder(@PathVariable("account") String account,
                                  @RequestParam("id") long id,
                                  HttpServletRequest request) {
        //加一个账号项，做验证
        try {
            return orderService.getOrder(account, id, request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求出错，请稍后重试");
        }
    }


    @PostMapping("/{account}/order/add")
    public Result<String> addOrder(@PathVariable("account") String account,
                                   @RequestBody OrderForm form,
                                   HttpServletRequest request) {
        try {
            return orderService.doOrderAdd(account, form, request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求出错，请稍后重试");
        }
    }


    @PostMapping("/{account}/order/receiver/update")
    public Result<String> updateReceiverInfo(@PathVariable("account") String account,
                                             @RequestBody OrderForm form,
                                             HttpServletRequest request) {
        try {
            return orderService.doReceiverInfoUpdate(account, form, request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("请求出错，请稍后重试");
        }
    }
}
