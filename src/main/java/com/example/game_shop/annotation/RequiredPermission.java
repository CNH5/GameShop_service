package com.example.game_shop.annotation;

import java.lang.annotation.*;

/**
 * @author sheng
 * @date 2021/12/3 17:01
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequiredPermission {

}
