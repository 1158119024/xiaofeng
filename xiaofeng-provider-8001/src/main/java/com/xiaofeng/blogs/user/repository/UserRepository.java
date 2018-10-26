package com.xiaofeng.blogs.user.repository;

import com.xiaofeng.blogs.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/25 17:17
 * @Description:
 */
@Mapper
public interface UserRepository {

    @Select("select * from xiaofeng_user where id = #{userId}")
    UserEntity get(Integer userId);
}
