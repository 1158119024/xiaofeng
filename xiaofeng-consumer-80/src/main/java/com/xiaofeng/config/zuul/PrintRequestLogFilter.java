package com.xiaofeng.config.zuul;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xiaofeng.base.httpformat.ResponseData;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/22 15:45
 * @Description:
 */
@Configuration
public class PrintRequestLogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;//要打印返回信息，必须得用"post"
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        try {
            HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
            Object zuulResponse = RequestContext.getCurrentContext().get("zuulResponse");
            if (zuulResponse != null) {
                RibbonHttpResponse resp = (RibbonHttpResponse) zuulResponse;
                String body = IOUtils.toString(new InputStreamReader(resp.getBody()));
                ResponseData responseData = JSON.parseObject(body, ResponseData.class);
                String token = responseData.getToken();
                // 把token存入客户端
                if ( !StringUtils.isEmpty(token) ){
                    response.setHeader("set-cookie", "token=" + token + ";path=/;");
                }
                // 没有登陆，跳登陆页面
                if( responseData.getCode() != null && responseData.getCode() == 203 ){
//                    response.setHeader("set-cookie", "token= ;path=/;");
//                    response.sendRedirect("http://192.168.230.1:4444/#/user/login");
                }
                responseData.setToken(null);
                String jsonBody = JSON.toJSONString(responseData);
                RequestContext.getCurrentContext().setResponseBody(jsonBody);
                resp.close();
            }
//            zuulResponse.setHeader("set-cookie", "12312313132");
//            System.out.println(JSON.toJSONString(zuulResponse));

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*String printArray(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }*/
}
