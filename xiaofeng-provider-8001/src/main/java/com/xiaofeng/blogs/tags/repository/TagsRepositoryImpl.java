package com.xiaofeng.blogs.tags.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/4 23:22
 * @Description:
 */
public class TagsRepositoryImpl {

    //@Select("select * from xiaofeng_tags where userId = #{userId}")
    public String getTagsByUserId(final @Param("userId")  Integer userId, final @Param("tagName") String tagName){
        return new SQL() {
            {
                SELECT("*");
                FROM("xiaofeng_tags");
                WHERE("1=1");
                WHERE("userId = #{userId}");
                if( !StringUtils.isEmpty(tagName) ){
                    WHERE("tagName like CONCAT('%',#{tagName},'%')");
                }
            }
        }.toString();
    }
}
