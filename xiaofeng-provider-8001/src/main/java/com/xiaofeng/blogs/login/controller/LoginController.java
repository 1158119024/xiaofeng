package com.xiaofeng.blogs.login.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.login.service.LoginService;
import com.xiaofeng.blogs.user.entity.UserEntity;
import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.aop.AopUtils;
import com.xiaofeng.config.jwt.TokenEntity;
import com.xiaofeng.config.jwt.TokenUtil;
import com.xiaofeng.utils.EncryptionUtils;
import com.xiaofeng.utils.MD5Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 17:47
 * @Description:
 */
@Log4j2
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestBody UserEntity user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if( StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()) ){
            return ResponseData.fial("登录失败,参数不全");
        }
        String md5Password = MD5Utils.md5Password(EncryptionUtils.aesDecrypt(user.getPassword()));
        user.setPassword(md5Password);
        //未登录
        user = loginService.login(user);
        if( user == null ){
            return ResponseData.fial("登录失败,没有找到对应的用户。");
        }
        TokenEntity tokenEntity = new TokenEntity(user.getId(), user.getUsername());
        String token = TokenUtil.getJWTString(tokenEntity, new HashMap<>());
        return ResponseData.successToken(resultMap, token);
    }

    @IsLogin
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    public ResponseData isLogin(HttpServletRequest request){
        String token = AopUtils.getToken(request);
        return ResponseData.success(TokenUtil.isValid(token));
    }

    @IsLogin
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public ResponseData loginOut(HttpServletRequest request){
        String token = AopUtils.getToken(request);
        return ResponseData.notLogin();
    }

}
