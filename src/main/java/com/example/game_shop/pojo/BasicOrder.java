package com.example.game_shop.pojo;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * @author sheng
 * @date 2021/11/18 20:47
 */
@Data
public class BasicOrder {
    @NonNull
    private long id;

    @NonNull
    private String type;

    @NonNull
    private String date;

    private String express_delivery_id;

    @NonNull
    private List<OrderGame> games;
}
