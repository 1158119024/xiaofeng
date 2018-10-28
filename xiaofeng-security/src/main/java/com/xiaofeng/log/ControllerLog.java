package com.xiaofeng.log;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.checklogin.aop.AopUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
        // 打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址: " + request.getRequestURL().toString());
        log.info("请求方式: " + request.getMethod());
        log.info("请求类方法: " + joinPoint.getSignature());
        log.info("请求类方法参数: " + Arrays.toString(joinPoint.getArgs()));
        log.info("请求token: " + AopUtils.getToken(request));

        Object[] args = joinPoint.getArgs();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName(); // 获取方法名称
        // 获取参数名称和值
        StringBuffer sb = AopUtils.getNameAndArgs(this.getClass(), clazzName, methodName, args);
        log.info("请求类方法参数名称和值: " + sb);
        Object result = joinPoint.proceed();
        log.info("请求返回值: " + JSON.toJSONString(result));
        log.info("===============请求内容===============");
        return result;
    }

    @Pointcut("execution(* com.xiaofeng.blogs.*.service..*.*(..))")
    public void exceptionService(){
    }
    @AfterThrowing(value = "executeService()", throwing = "ex")
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

        XFException exp = new XFException();
        exp.setExClass(joinPoint.getTarget().getClass()+"-->"+exclass);
        exp.setException(ex.toString());
        exp.setMethod(joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName()+"()-->"+exclass+"."+ method+"()");
        exp.setExceptionContent(exceptionContent);
        exp.setParams(params);
        exp.setExTime(new Date());
//        exceptionService.saveException(exp);
        log.error(exp);
    }

}
