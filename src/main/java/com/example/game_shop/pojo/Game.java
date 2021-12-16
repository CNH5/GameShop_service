package com.example.game_shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/17 9:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private long id;

    @NonNull
    private String name;

    @NonNull
    private String platform;

    private int stock;

    @NonNull
    private double price;

    @NonNull
    private String status;

    private String cover_image;

    private List<String> images;

    private List<Map<String, Object>> history_price;
}
