package com.xiaofeng.blogs.session.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/22 12:06
 * @Description: 获取sessionId
 *
 */
@FeignClient(name = "xiaofeng-provider")
public interface SessionConsumerController {

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String getSession();
}
