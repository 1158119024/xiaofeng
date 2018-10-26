package com.xiaofeng.blogs.collect.repository;

import com.xiaofeng.blogs.collect.entity.CollectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/16 17:01
 * @Description:
 */
@Mapper
public interface CollectRepository {

    @Select("select * from xiaofeng_collect")
    List<CollectEntity> list();
}
