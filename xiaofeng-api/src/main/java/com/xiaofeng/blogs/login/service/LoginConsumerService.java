package com.xiaofeng.blogs.login.service;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.user.entity.UserEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.invoke.MethodType;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 17:25
 * @Description: 登陆api接口
 */
//@FeignClient(name = "xiaofeng-provider", fallbackFactory = LoginConsumerServiceImpl.class)
@FeignClient(name = "xiaofeng-provider")
public interface LoginConsumerService {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(UserEntity user);

    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    public ResponseData isLogin();

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public ResponseData loginOut();

}
