package com.xiaofeng.log;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.checklogin.aop.AopUtils;
import com.xiaofeng.message.log.entity.LogEntity;
import com.xiaofeng.message.log.service.LogMessageHandleConsumerService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/26 22:21
 * @Description: 控制层日志监听
 */
@Component
@Aspect
@Log4j2
public class ControllerLog {

    @Autowired
    private LogMessageHandleConsumerService logMessageHandleConsumerService;

    @Pointcut("execution(* com.xiaofeng.blogs.*.controller..*.*(..))")
    public void executeService(){
    }

    /**
     * 环绕通知，校验用户登陆
     * @param joinPoint
     */
    @Around("executeService()")
    public Object doBeforeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        Object[] args = joinPoint.getArgs();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName(); // 获取方法名称
        ResponseData result = (ResponseData)joinPoint.proceed();
        // 打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址: " + request.getRequestURL().toString());
        log.info("请求方式: " + request.getMethod());
        log.info("请求的类: " + joinPoint.getTarget().getClass());
        log.info("请求的方法: " + joinPoint.getSignature().getName());
        log.info("请求类方法参数: " + JSON.toJSONString(Arrays.toString(joinPoint.getArgs())));
        // 获取参数名称和值
        StringBuffer sb = AopUtils.getNameAndArgs(this.getClass(), clazzName, methodName, args);
        log.info("请求的参数名称和值: " + sb);
        log.info("请求token: " + AopUtils.getToken(request));
        log.info("请求返回值: " + JSON.toJSONString(result));
        log.info("===============请求内容===============");
//        LogEntity logEntity = new LogEntity();
//        logEntity.setCode(result.getCode());
//        logEntity.setAddress(request.getRequestURL().toString());
//        logEntity.setMethodType(request.getMethod());
//        logEntity.setClazz(joinPoint.getTarget().getClass().toString());
//        logEntity.setMethod(joinPoint.getSignature().getName());
//        logEntity.setParams(sb.toString());
//        logEntity.setToken(AopUtils.getToken(request));
//        logEntity.setReturnValue(JSON.toJSONString(result));
//        logEntity.setExTime(new Date());
//        try{
//            logMessageHandleConsumerService.add("6666");
//        }catch (Exception e){
//            log.error("记录日志请求错误", e);
//        }
        return result;
    }

    @Pointcut("execution(* com.xiaofeng.blogs.*.service..*.*(..))")
    public void exceptionService(){
    }
//    @AfterThrowing(value = "executeService()", throwing = "ex")
    public void exceptionHandlerAfterExp(JoinPoint joinPoint, Throwable ex){
        StackTraceElement[] trace = ex.getStackTrace();
        String exclass = trace[0].getClassName();
        String method = trace[0].getMethodName();
        String exceptionContent = "";
        for (StackTraceElement s : trace) {
            exceptionContent += "\tat " + s + "\r\n";
        }

        String params = joinPoint.getSignature().getName() + "( ";
        for(Object arg : joinPoint.getArgs()){
            params += arg+", ";

        }
        if(params.indexOf(",")  > 0){
            params = params.substring(0,params.length() -2) + " )";
        }else{
            params = "";
        }

        LogEntity exp = new LogEntity();
        exp.setCode(500);
        exp.setClazz(joinPoint.getTarget().getClass()+"-->"+exclass);
        exp.setException(ex.toString());
        exp.setMethod(joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName()+"()-->"+exclass+"."+ method+"()");
        exp.setExceptionContent(exceptionContent);
        exp.setParams(params);
        exp.setExTime(new Date());
//        exceptionService.saveException(exp);
        log.error(exp);
    }

}
