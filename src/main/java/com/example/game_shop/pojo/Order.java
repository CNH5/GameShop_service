package com.example.game_shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 12:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;

    @NonNull
    private String date;

    @NonNull
    private String type;

    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private String phoneNumber;

    private String express_delivery_company;

    private String express_delivery_id;

    @NonNull
    private List<OrderGame> games;
}
