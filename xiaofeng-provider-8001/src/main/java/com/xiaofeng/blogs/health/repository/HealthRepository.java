package com.xiaofeng.blogs.health.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/17 22:08
 * @Description:
 */
@Mapper
public interface HealthRepository {

    @Select("SELECT 1")
    public Long check();
}
