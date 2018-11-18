package com.xiaofeng.blogs.tags.service;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.tags.bo.TagsBo;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 10:52
 * @Description:
 */
@FeignClient(value = "xiaofeng-provider")
public interface TagsConsumerService {

    @RequestMapping(value = "/tags/add", method = RequestMethod.POST)
    ResponseData add(TagsEntity tagsEntity);

    @RequestMapping(value = "/tags/delete/{id}", method = RequestMethod.GET)
    ResponseData delete(@PathVariable("id") Integer id);

    @RequestMapping(value = "/tags/update", method = RequestMethod.POST)
    ResponseData update(TagsEntity tagsEntity);

    @RequestMapping(value = "/tags/incr", method = RequestMethod.POST)
    ResponseData incr(Integer id);

    @RequestMapping(value = "/tags/getTagById/{id}", method = RequestMethod.GET)
    ResponseData getTagById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/tags/getTagsByUserId", method = RequestMethod.POST)
    ResponseData getTagsByUserId(Map<String, String> map);

    @RequestMapping(value = "/tags/getTags", method = RequestMethod.POST)
    ResponseData getTags(TagsBo tagsBo);
}
