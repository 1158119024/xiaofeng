package com.xiaofeng.blogs.user.service;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.user.dto.UserAllDetailsDto;
import com.xiaofeng.blogs.user.entity.UserEntity;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/25 17:22
 * @Description:
 */
public interface UserService {

    UserEntity get(Integer userId) throws RuntimeException;

    UserAllDetailsDto getUserDetails(String username);
}
