package com.example.game_shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author sheng
 * @date 2021/11/17 23:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecyclePackGame {
    private long id;

    private int num;

    @NonNull
    private String name;

    private double now_price;

    private double old_price;

    private String cover_image;

    @NonNull
    private String platform;

    @NonNull
    private String status;

    private int stock;
}
