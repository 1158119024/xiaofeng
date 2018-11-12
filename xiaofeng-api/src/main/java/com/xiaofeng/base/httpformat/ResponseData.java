package com.xiaofeng.base.httpformat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/16 22:52
 * @Description: 前后交互封装响应数据
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class ResponseData {

    /*
        编码:
            200: 成功
            400: (错误请求)服务器不理解请求的语法。
            408: (请求超时)服务器等候请求时发生超时。
            203: (非授权信息)服务器已成功处理了请求，但返回的信息可能来自另一来源。
            201: (已创建)	请求成功并且服务器创建了新的资源。
            500: (服务器内部错误) 服务器遇到错误，无法完成请求。
     */
    private Integer code;
    private String msg;
    private Object data;
    private String token;

    public ResponseData(Integer code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseData success(Object data){
        return new ResponseData(200, "操作成功！！", data);
    }

    public static ResponseData successToken(Object data, String token){
        ResponseData result = ResponseData.success(data);
        result.setToken(token);
        return result;
    }

    public static ResponseData fial(String msg){
        return new ResponseData(400, msg, null);
    }

    public static ResponseData fial(){
        return new ResponseData(400, "操作失败！！", null);
    }

    public static ResponseData addFial(){
        return new ResponseData(400, "增加失败！！重复添加", null);
    }

    public static ResponseData _500(){
        return new ResponseData(500, "服务器内部错误", null);
    }

    public static ResponseData _201(Object data){
        return new ResponseData(201, "请求成功,服务器创建了新的资源", data);
    }

    public static ResponseData hystrix(){
        return new ResponseData(408, "服务发生错误或已关闭，无法提供正常服务---服务熔断机制", null);
    }

    public static ResponseData notLogin(){
        return new ResponseData(203, "您还没有登陆呢", null);
    }
}
