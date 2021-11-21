package com.example.game_shop.service;

import com.example.game_shop.mapper.OrderGameMapper;
import com.example.game_shop.mapper.OrderMapper;
import com.example.game_shop.pojo.BasicOrder;
import com.example.game_shop.pojo.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 22:18
 */
@Service
public class OrderService {
    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderGameMapper orderGameMapper;

    public boolean hasOrder(String account, long id) {
        return orderMapper.getOrder(account, id) != null;
    }

    public List<BasicOrder> getOrderList(String account, String type) {
        return orderMapper.getOrderList(account, type);
    }

    public Order getOrder(String account, long id) {
        return orderMapper.getOrder(account, id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addOrder(String account, Order order) {
        int orderInserted = orderMapper.insert(account, order);
        int gameInserted = orderGameMapper.insertGame(order);

        System.out.println("insert order: " + orderInserted);
        System.out.println("insert game: " + gameInserted);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateReceiverInfo(String account, Order order) {
        int updated = orderMapper.updateReceiverInfo(account, order);
        System.out.println("update receiver: " + updated);
    }
}
