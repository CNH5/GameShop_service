package com.example.game_shop.bean;

import java.util.regex.Pattern;

/**
 * @author sheng
 * @date 2021/12/14 20:40
 */
public class Const {
    public static final Pattern phoneNumber =
            Pattern.compile("^1[3456789][0-9]{9,11}$");
}
