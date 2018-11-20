package com.xiaofeng.blogs.collect.repository;

import com.xiaofeng.blogs.collect.bo.CollectBo;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import com.xiaofeng.utils.MybatisUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/20 16:55
 * @Description:
 */
public class CollectRepositoryImpl {

    public String add(final CollectEntity collectEntity) {
        List<String> excludeFields = new ArrayList<>();// 需要排除的字段
        excludeFields.add("createTime");
        Map<String, String> add = MybatisUtils.add(collectEntity, excludeFields);
        return new SQL(){
            {
                INSERT_INTO("xiaofeng_collect");
                VALUES(add.get("key"), add.get("value"));
                VALUES("createTime", "now()");
            }
        }.toString();
    }

    public String update(final CollectEntity collectEntity){
        List<String> excludeFields = new ArrayList<>();// 需要排除的字段
        String sets = MybatisUtils.update(collectEntity, excludeFields);
        return new SQL(){
            {
                UPDATE("xiaofeng_collect");
                SET(sets);
                WHERE("id=#{id}");
                WHERE("userId=#{userId}");
            }
        }.toString();
    }

    public String getCollectsByCondition(final CollectBo collectBo){
        return new SQL() {
            {
                SELECT("*");
                FROM("xiaofeng_collect");
                WHERE("userId = #{userId}");
                if( !StringUtils.isEmpty(collectBo.getTitle())){
                    WHERE("title like CONCAT('%',#{title},'%')");
                }
                if( !StringUtils.isEmpty(collectBo.getTagId())){
                    WHERE("tagsId like CONCAT('%',#{tagId},'%')");
                }
            }
        }.toString();
    }
}
