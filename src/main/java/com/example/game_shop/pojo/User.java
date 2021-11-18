package com.example.game_shop.pojo;

import lombok.Data;
import lombok.NonNull;

/**
 * @author sheng
 * @date 2021/11/17 8:43
 */
@Data
public class User {
    @NonNull private String account;
    private String password;
    private String name;
    private String avatar;
    private String gender;
}
