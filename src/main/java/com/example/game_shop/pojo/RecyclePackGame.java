package com.example.game_shop.pojo;

import lombok.Data;

/**
 * @author sheng
 * @date 2021/11/17 23:58
 */
@Data
public class RecyclePackGame {
    private long id;
    private int num;
    private String name;
    private double now_price;
    private double old_price;
    private String platform;
    private String status;
    private int stock;
}
