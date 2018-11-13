package com.xiaofeng.blogs.article.service;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 20:37
 * @Description: 文章接口
 */
@FeignClient(name = "xiaofeng-provider")
public interface ArticleConsumerService {

    @RequestMapping(value = "/article/add", method = RequestMethod.POST)
    ResponseData add(ArticleEntity articleEntity);

    @RequestMapping(value = "/article/delete/{id}", method = RequestMethod.GET)
    ResponseData delete(@PathVariable("id") Integer id);

    @RequestMapping(value = "/article/update", method = RequestMethod.POST)
    ResponseData update(ArticleEntity articleEntity);

    @RequestMapping(value = "/article/getArticleById/{id}", method = RequestMethod.GET)
    ResponseData getArticleById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/article/getArticlesByUserId", method = RequestMethod.POST)
    ResponseData getArticlesByUserId(ArticleBo articleBo);
}
