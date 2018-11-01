package com.xiaofeng.blogs.user.controller;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.user.entity.UserEntity;
import com.xiaofeng.blogs.user.service.UserService;
import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.aop.AopUtils;
import com.xiaofeng.checklogin.aop.CheckLoginAop;
import com.xiaofeng.config.jwt.TokenEntity;
import com.xiaofeng.config.jwt.TokenUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/25 16:09
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @IsLogin
    @RequestMapping("/get")
    public ResponseData get(HttpServletRequest request){
        TokenEntity tokenEntity = TokenUtil.parseJWTtoUserEntity(AopUtils.getToken(request));
        UserEntity userEntity = userService.get(tokenEntity.getUserId());
        return ResponseData.success(userEntity);
    }

    @RequestMapping(value = "/getUserDetails/{username}", method = RequestMethod.GET)
    public ResponseData getUserDetails(@PathVariable String username){
        return ResponseData.success(userService.getUserDetails(username));
    }
}
