package com.example.game_shop.Result;

/**
 * @author sheng
 * @date 2021/11/28 22:10
 */
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(200),

    /**
     * 失败
     */
    FAIL(400),

    /**
     * 接口不存在
     */
    NOT_FOUND(404),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultEnum(int code) {
        this.code = code;
    }
}
