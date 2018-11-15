package com.xiaofeng.utils;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.blogs.article.entity.ArticleEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/14 21:27
 * @Description:
 */
public class MybatisUtils {

    /**
     *
     * @param obj
     * @param excludeFields: 需要排除的属性名称
     * @return
     */
    public static Map<String, String> add(Object obj, List<String> excludeFields){
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        String key = "";
        String value = "";
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                Object o = f.get(obj);
                if( o != null ){
                    String fName = f.getName();
                    if( !excludeFields.contains(fName) ){
                        key += fName + ",";
                        value += "#{"+fName+"},";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        key = key.substring(0, key.length() - 1);
        value = value.substring(0, value.length() - 1);
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("value", value);
        return map;
    }

    public static String update(Object obj, List<String> excludeFields){
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        String sets = "";
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                Object o = f.get(obj);
                if( o != null ){
                    String fName = f.getName();
                    if( !excludeFields.contains(fName) ){
                        sets += fName + "=#{"+fName+"},";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sets = sets.substring(0, sets.length() - 1);
        return sets;
    }

    public static void main(String[] args) {
        ArticleEntity articleEntity = new ArticleEntity();
        System.out.println(JSON.toJSONString(articleEntity));
        articleEntity.setId(123).setUserId(123).setTopGrade(1);
//        Map<String, String> map = add(articleEntity);
//        System.out.println(map);
    }
}
