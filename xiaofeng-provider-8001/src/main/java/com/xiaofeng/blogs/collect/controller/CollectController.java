package com.xiaofeng.blogs.collect.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.collect.dto.CollectDto;
import com.xiaofeng.blogs.collect.service.CollectService;
//import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.annotation.IsLogin;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * @auther: 晓枫
 * @date: 2018/10/16 17:00
 * @Description: 收藏Controller
 */
@RestController
@Log4j2
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @IsLogin
    @RequestMapping("/list")
    public ResponseData list(String token){
        List<CollectDto> list = collectService.list();
        return ResponseData.success(list);
    }

    @RequestMapping(value = "/first", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Map<String, Object> firstResp (HttpServletRequest request, HttpServletResponse response){
        response.setHeader("xiaofeng", "xiaofeng");
        response.setHeader("set-cookie", "123");
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("user", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        Map<String, String> stringStringHashMap = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String key: headerNames) {
            String header = response.getHeader(key);
            map.put(key, header);
        }
        stringStringHashMap.put("set-cookie", "123132132");
        map.put("header", stringStringHashMap);
        return map;
    }

//    @IsLogin
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request, HttpServletResponse response){
//        response.setHeader("set-cookie", "xiaofeng=xiaofeng; path=/");
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession(false).getId());
        map.put("message", request.getSession(false).getAttribute("user"));
        return map;
    }



}
