package com.xiaofeng.blogs.collect.service;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.collect.bo.CollectBo;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import com.xiaofeng.utils.GetHtmlTitle;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    ResponseData list();

    @RequestMapping(value = "/collect/getTitleByUrl", method = RequestMethod.POST)
    ResponseData getTitleByUrl(String url);

    @RequestMapping(value = "/collect/add", method = RequestMethod.POST)
    ResponseData add(CollectEntity collectEntity);

    @RequestMapping(value = "/collect/delete/{id}", method = RequestMethod.GET)
    ResponseData delete(@PathVariable("id") Integer id);

    @RequestMapping(value = "/collect/update", method = RequestMethod.POST)
    ResponseData update(CollectEntity collectEntity);

    @RequestMapping(value = "/collect/getCollectsByCondition", method = RequestMethod.POST)
    ResponseData getCollectsByCondition(CollectBo collectBo);

}
