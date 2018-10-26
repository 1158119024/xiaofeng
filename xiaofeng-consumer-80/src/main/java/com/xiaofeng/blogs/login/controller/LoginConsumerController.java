package com.xiaofeng.blogs.login.controller;

import com.netflix.discovery.converters.Auto;
import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.login.service.LoginConsumerService;
import com.xiaofeng.blogs.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/21 10:47
 * @Description:
 */
@RestController
public class LoginConsumerController {

    @Autowired
    private LoginConsumerService loginConsumerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestBody UserEntity user){
        return loginConsumerService.login(user);
    }

    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    public ResponseData isLogin(){
        return loginConsumerService.isLogin();
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public ResponseData loginOut(){
        return loginConsumerService.loginOut();
    }
}
