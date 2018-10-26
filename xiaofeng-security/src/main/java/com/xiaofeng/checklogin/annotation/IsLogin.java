package com.xiaofeng.checklogin.annotation;

import java.lang.annotation.*;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 15:58
 * @Description: 判断是否需要登陆的注解标识  有:则代表需要登陆
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsLogin {
}
