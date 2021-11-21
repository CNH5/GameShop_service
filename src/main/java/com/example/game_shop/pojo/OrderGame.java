package com.example.game_shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author sheng
 * @date 2021/11/17 23:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGame {
    @NonNull
    private long id;

    @NonNull
    private String name;

    private int num;

    private double price;

    @NonNull
    private String status;

    private String cover_image;
}
