package com.xiaofeng.utils;

import java.util.UUID;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/26 15:31
 * @Description:
 */
public class UUIDUtils {

   public static String randomUUID(){
       return UUID.randomUUID().toString().replaceAll("-", "");
   }
}
