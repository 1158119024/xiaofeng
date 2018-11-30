package com.xiaofeng.message.log.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/26 22:42
 * @Description:
 */
@FeignClient(name = "xiaofeng-message-handle", fallbackFactory = LogMessageHandleConsumerServiceImpl.class)
public interface LogMessageHandleConsumerService {

    @RequestMapping(value = "/log/add/{id}", method = RequestMethod.GET)
    void add(@PathVariable("id") String id);
}
