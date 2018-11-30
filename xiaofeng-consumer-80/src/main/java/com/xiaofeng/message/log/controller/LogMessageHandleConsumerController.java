package com.xiaofeng.message.log.controller;

import com.xiaofeng.message.log.service.LogMessageHandleConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/26 22:41
 * @Description:
 */
@RestController
@RequestMapping("/log")
public class LogMessageHandleConsumerController {

    @Autowired
    private LogMessageHandleConsumerService logMessageHandleConsumerService;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public void add(@PathVariable String id){
        logMessageHandleConsumerService.add(id);
    }
}
