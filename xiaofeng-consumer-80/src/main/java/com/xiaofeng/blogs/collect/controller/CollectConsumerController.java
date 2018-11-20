package com.xiaofeng.blogs.collect.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.collect.bo.CollectBo;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import com.xiaofeng.blogs.collect.service.CollectConsumerService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getTitleByUrl", method = RequestMethod.POST)
    public ResponseData getTitleByUrl(@RequestParam String url){
        return collectConsumerService.getTitleByUrl(url);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody CollectEntity collectEntity){
        return collectConsumerService.add(collectEntity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseData delete(@PathVariable("id") Integer id){
        return collectConsumerService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(@RequestBody CollectEntity collectEntity){
        return collectConsumerService.update(collectEntity);
    }

    @RequestMapping(value = "/getCollectsByCondition", method = RequestMethod.POST)
    public ResponseData getCollectsByCondition(@RequestBody CollectBo collectBo){
        return collectConsumerService.getCollectsByCondition(collectBo);
    }
}
