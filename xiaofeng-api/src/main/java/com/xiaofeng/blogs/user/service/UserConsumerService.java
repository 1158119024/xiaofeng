package com.xiaofeng.blogs.user.service;

import com.xiaofeng.base.httpformat.ResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/25 20:59
 * @Description:
 */
@FeignClient(value = "xiaofeng-provider")
public interface UserConsumerService {

    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    ResponseData get();

    @RequestMapping(value = "/user/getUserDetails/{username}", method = RequestMethod.GET)
    ResponseData getUserDetails(@PathVariable("username") String username);

}
