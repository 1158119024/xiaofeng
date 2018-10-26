package com.xiaofeng.blogs.login.repository;

import com.xiaofeng.blogs.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 17:53
 * @Description:
 */
@Mapper
public interface LoginRepository {

    @Select("select * from xiaofeng_user where username = #{username} and password = #{password}")
    UserEntity login(UserEntity user);
}
