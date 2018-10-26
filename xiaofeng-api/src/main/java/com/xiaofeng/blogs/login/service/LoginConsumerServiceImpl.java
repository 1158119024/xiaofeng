package com.xiaofeng.blogs.login.service;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.user.entity.UserEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 17:27
 * @Description: 登陆熔断
 */
//@Component
/*public class LoginConsumerServiceImpl implements FallbackFactory<LoginConsumerService> {

    @Override
    public LoginConsumerService create(Throwable throwable) {
        return new LoginConsumerService() {
            @Override
            public ResponseData login(UserEntity user) {
                return ResponseData.hystrix();
            }

            @Override
            public ResponseData isLogin() {
                return ResponseData.hystrix();
            }

            @Override
            public ResponseData loginOut() {
                return ResponseData.hystrix();
            }
        };
    }
}*/
