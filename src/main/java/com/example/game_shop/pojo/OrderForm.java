package com.example.game_shop.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/30 11:21
 */
@Data
public class OrderForm {
    private long id;
    private String name;
    private String type;
    private String location;
    private String phoneNumber;
    /**
     * [{"id": id, "num": num}]
     */
    private List<Map<String, Object>> games;
}
