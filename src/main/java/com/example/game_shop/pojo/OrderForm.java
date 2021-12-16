package com.example.game_shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author sheng
 * @date 2021/11/30 11:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
    private long id;
    private String account;
    private String name;
    private String type;
    private String location;
    private String phoneNumber;
    /**
     * [{"id": id, "num": num}]
     */
    private List<Map<String, Object>> games;
}
