package com.example.game_shop.pojo;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/17 9:18
 */
@Data
public class Game {
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String platform;

    private int stock;

    @NonNull
    private double price;

    private List<String> images;

    private List<Map<String, String>> history_price;
}
