package com.example.game_shop.annotation;

import java.lang.annotation.*;

/**
 * 注解表示请求不需要token也能访问
 *
 * @author sheng
 * @date 2021/12/3 17:21
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DoWithoutToken {

}
