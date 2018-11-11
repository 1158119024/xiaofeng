package com.xiaofeng.blogs.tags.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.service.TagsConsumerService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 15:13
 * @Description:
 */
@RestController
@RequestMapping("/tags")
public class TagsConsumerController {

    @Autowired
    private TagsConsumerService tagsConsumerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody TagsEntity tagsEntity){
        return tagsConsumerService.add(tagsEntity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseData delete(@PathVariable("id") Integer id){
        return tagsConsumerService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(@RequestBody TagsEntity tagsEntity){
        return tagsConsumerService.update(tagsEntity);
    }

    @RequestMapping(value = "/incr", method = RequestMethod.POST)
    public ResponseData incr(@RequestBody Integer id){
        return tagsConsumerService.incr(id);
    }

    @RequestMapping(value = "/getTagById/{id}", method = RequestMethod.GET)
    public ResponseData getTagById(@PathVariable("id") Integer id){
        return tagsConsumerService.getTagById(id);
    }

    @RequestMapping(value = "/getTagsByUserId", method = RequestMethod.POST)
    public ResponseData getTagsByUserId(@RequestBody Map<String, String> map){
        return tagsConsumerService.getTagsByUserId(map);
    }
}
