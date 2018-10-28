package com.xiaofeng.checklogin.aop;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.login.service.LoginConsumerService;
import com.xiaofeng.blogs.user.entity.UserEntity;
import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.config.jwt.TokenEntity;
import com.xiaofeng.config.jwt.TokenUtil;
import io.jsonwebtoken.Claims;
import jdk.nashorn.internal.parser.Token;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 15:42
 * @Description: 校验是否登陆
 */
@Component
@Aspect
public class CheckLoginAop {

    @Pointcut("execution(* com.xiaofeng.blogs.*.controller..*.*(..))")
    public void executeService(){
    }

    /**
     * 环绕通知，校验用户登陆
     * @param joinPoint
     */
    @Around("executeService()")
    public Object doBeforeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        boolean isLogin = false;
        // 1.获取类上或方法上的注解来确认是否需要判断登陆
        // 方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        Method method = methodSignature.getMethod();
        isLogin = method.isAnnotationPresent(IsLogin.class);

        // 2.判断是否登陆
        if( isLogin ){
            // 需要登陆
            HttpServletRequest request = (HttpServletRequest)RequestContextHolder.getRequestAttributes().resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String token = AopUtils.getToken(request);
            // token失效, 退出登陆
            if( StringUtils.isEmpty(token) || !TokenUtil.isValid(token)){
                return ResponseData.notLogin();
            }
            // 续约||请求服务
            Map<String, Object> tokenMap = TokenUtil.parseJWTtoMap(token);
            Long expire = (Long)tokenMap.get(Claims.EXPIRATION);// 过期时间
            Long nowTime = System.currentTimeMillis();// 当前时间
            Long extensionTime = TokenUtil.EXPIRATION / 3 * 60 * 1000L;// 续约时间
            if ( expire - nowTime < extensionTime ){// 过期时间 - 当前时间 < 续约时间 --->续约
                // 校验账号是否在这段时间改过密码, 没改过才给续约, 防止token被盗用, 用户改密后可强制下线,
                TokenEntity tokenEntity = JSON.parseObject(JSON.toJSONString(tokenMap.get(Claims.ISSUER)), TokenEntity.class);
                // 请求服务
                ResponseData resultData = (ResponseData)joinPoint.proceed();
                // 直接续约, 不在演示校验
                resultData.setToken(TokenUtil.getJWTString(tokenEntity, new HashMap<>()));
                return resultData;
            }
            return joinPoint.proceed();
        }
        // 不需要登陆
        return joinPoint.proceed();
    }



}
