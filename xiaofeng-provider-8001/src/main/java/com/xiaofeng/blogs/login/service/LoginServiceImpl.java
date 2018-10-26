package com.xiaofeng.blogs.login.service;

import com.xiaofeng.blogs.login.repository.LoginRepository;
import com.xiaofeng.blogs.user.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 17:52
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginRepository loginRepository;

    @Override
    public UserEntity login(UserEntity user) {
        return loginRepository.login(user);
    }
}
