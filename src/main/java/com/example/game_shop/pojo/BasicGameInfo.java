package com.example.game_shop.pojo;

import lombok.Data;

/**
 * @author sheng
 * @date 2021/11/17 9:49
 */
@Data
public class BasicGameInfo {
    private long id;
    private String name;
    private double price;
    private String cover_image;
}
