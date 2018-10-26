package com.xiaofeng.blogs.collect.service;

import com.xiaofeng.base.httpformat.ResponseData;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


/**
 * @Auther: 晓枫
 * @Date: 2018/10/17 10:58
 * @Description: Feign WebService格式，rest风格访问提供者
 *      FeignClient
 *          name: 指定提供服务的application.name
 *          fallbackFactory: 指定服务发生错误后由谁来提供错误信息（服务降级）
 */
//@FeignClient(name = "xiaofeng-provider", fallbackFactory = CollectConsumerServiceImpl.class)
@FeignClient(name = "xiaofeng-provider")
public interface CollectConsumerService {

    @RequestMapping(value = "/collect/list", method = RequestMethod.GET)
    public ResponseData list();

    @RequestMapping(value = "/collect/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp ();


}
