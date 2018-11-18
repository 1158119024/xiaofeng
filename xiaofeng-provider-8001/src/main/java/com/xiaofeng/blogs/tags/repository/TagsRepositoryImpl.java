package com.xiaofeng.blogs.tags.repository;

import com.xiaofeng.blogs.tags.bo.TagsBo;
import com.xiaofeng.blogs.tags.dto.TagsDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

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
                ORDER_BY("createTime desc");
            }
        }.toString();
    }

    public String getTags(TagsBo tagsBo){
        return new SQL() {
            {
                SELECT("t.id, t.tagName, t.articleNum, t.createTime, u.aliasname");
                FROM("xiaofeng_tags t");
                INNER_JOIN("xiaofeng_user u on t.userId = u.id and t.userId = #{userId}");
                ORDER_BY("createTime desc");
            }
        }.toString();
    }
}
