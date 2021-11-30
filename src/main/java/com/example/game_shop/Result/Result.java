package com.example.game_shop.Result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author sheng
 * @date 2021/11/29 23:37
 */
@Data
@Accessors(chain=true)
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
}
