package com.xiaofeng.blogs.login.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.login.service.LoginService;
import com.xiaofeng.blogs.user.entity.UserEntity;
import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.aop.AopUtils;
import com.xiaofeng.checklogin.aop.CheckLoginAop;
import com.xiaofeng.config.jwt.TokenEntity;
import com.xiaofeng.config.jwt.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 17:47
 * @Description:
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestBody UserEntity user){
        Map<String, Object> resultMap = new HashMap<>();
        if( StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()) ){
            return ResponseData.fial("登录失败,参数不全");
        }
        //未登录
        user = loginService.login(user);
        if( user == null ){
            return ResponseData.fial("登录失败,没有找到对应的用户。");
        }
        TokenEntity tokenEntity = new TokenEntity(user.getId(), user.getUsername());
        String token = TokenUtil.getJWTString(tokenEntity, new HashMap<>());
//        resultMap.put("user", user);
        System.out.println("登录成功");
        return ResponseData.successToken(resultMap, token);
    }

    @IsLogin
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    public ResponseData isLogin(HttpServletRequest request){
        String token = AopUtils.getToken(request);
//        if( StringUtils.isEmpty(token) ){
//            return ResponseData.fial("缺少token");
//        }
        return ResponseData.success(TokenUtil.isValid(token));
    }

    @IsLogin
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public ResponseData loginOut(HttpServletRequest request){
        String token = AopUtils.getToken(request);
        return ResponseData.notLogin();
    }
}
