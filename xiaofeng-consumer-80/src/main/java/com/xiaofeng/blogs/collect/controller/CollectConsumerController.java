package com.xiaofeng.blogs.collect.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.collect.service.CollectConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @Auther: 晓枫
 * @Date: 2018/10/17 11:46
 * @Description:
 */
@RestController
@RequestMapping("/collect")
public class CollectConsumerController {


    @Autowired
    private CollectConsumerService collectConsumerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseData list(){
        return collectConsumerService.list();
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp (HttpServletResponse response){

        Map<String, Object> stringObjectMap = collectConsumerService.firstResp();

//        Map<String, String> header = (Map<String, String>)stringObjectMap.get("header");
//        Set<String> set = header.keySet();
//        for ( String key: set ) {
//            response.setHeader(key, header.get(key));
//        }
        return stringObjectMap;
    }
}
