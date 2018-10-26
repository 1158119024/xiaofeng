package com.xiaofeng.blogs.user.service;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.user.entity.UserEntity;
import com.xiaofeng.blogs.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/25 17:23
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserEntity get(Integer userId){
        return userRepository.get(userId);
    }
}
