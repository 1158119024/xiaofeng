package com.xiaofeng.blogs.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.collect.service.CollectConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Map<String, Object> firstResp (){

        Map<String, Object> stringObjectMap = collectConsumerService.firstResp();
        return stringObjectMap;
    }
}
