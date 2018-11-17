package com.xiaofeng.blogs.user.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.user.service.UserConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/25 21:01
 * @Description: user接口
 */
@RestController
@RequestMapping("/user")
public class UserConsumerController {

    @Autowired
    private UserConsumerService userConsumerService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseData get(){
        return userConsumerService.get();
    }

    @RequestMapping(value = "/getUserDetails/{userId}", method = RequestMethod.GET)
    public ResponseData getUserDetails(@PathVariable Integer userId){
        return userConsumerService.getUserDetails(userId);
    }
}
