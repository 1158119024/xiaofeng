package com.xiaofeng.queue.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/26 22:34
 * @Description:
 */
@Log4j2
@RequestMapping("/log")
@RestController
public class LogMessageHandleController {

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public void add(@PathVariable("id") String id){
        log.info(id);
        log.error(id);
        System.out.println(id);
    }
}
