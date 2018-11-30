package com.xiaofeng.message.log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/27 17:34
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class LogEntity {

    // 成功或失败
    private Integer code;
    // 请求方式
    private String methodType;
    // 请求的地址
    private String address;
    // 请求的类
    private String clazz;
    // 请求的方法
    private String method;
    // 请求参数
    private String params;
    // 请求的token
    private String token;
    // 返回值
    private String returnValue;
    // 发生的异常
    private String exception;
    // 异常信息
    private String exceptionContent;
    // 时间
    private Date exTime;
}
