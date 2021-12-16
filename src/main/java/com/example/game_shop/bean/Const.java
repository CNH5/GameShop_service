package com.example.game_shop.bean;

import java.util.regex.Pattern;

/**
 * @author sheng
 * @date 2021/12/14 20:40
 */
public class Const {
    public static final Pattern phoneNumber =
            Pattern.compile("^(13[0-9]|14[01456879]|15[0-3,5-9]|16[2567]|17[0-8]|18[0-9]|19[0-3,5-9])d{8}$");
}
