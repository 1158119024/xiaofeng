package com.xiaofeng.blogs.article.controller;

import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.article.bo.ArchivesBo;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.service.ArticleConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/12 14:45
 * @Description:
 */
@RestController
@RequestMapping("/article")
public class ArticleConsumerController {

    @Autowired
    private ArticleConsumerService articleConsumerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody ArticleEntity articleEntity){
        return articleConsumerService.add(articleEntity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseData delete(@PathVariable("id") Integer id){
        return articleConsumerService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(@RequestBody ArticleEntity articleEntity){
        return articleConsumerService.update(articleEntity);
    }

    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    public ResponseData updateState(@RequestBody ArticleEntity articleEntity){
        return articleConsumerService.updateState(articleEntity);
    }

    @RequestMapping(value = "/getArticleById/{id}", method = RequestMethod.GET)
    public ResponseData getArticleById(@PathVariable("id") Integer id){
        return articleConsumerService.getArticleById(id);
    }

    @RequestMapping(value = "/getArticleAndPreAndNextById", method = RequestMethod.POST)
    public ResponseData getArticleAndPreAndNextById(@RequestBody ArticleBo articleBo){
        return articleConsumerService.getArticleAndPreAndNextById(articleBo);
    }

    @RequestMapping(value = "/getArticlesByUserId", method = RequestMethod.POST)
    public ResponseData getArticlesByUserId(@RequestBody ArticleBo articleBo){
        return articleConsumerService.getArticlesByUserId(articleBo);
    }

    @RequestMapping(value = "/getArticles", method = RequestMethod.POST)
    public ResponseData getArticles(@RequestBody ArticleBo articleBo){
        return articleConsumerService.getArticles(articleBo);
    }

    /**
     * 根据创建时间归档查询
     * @return
     */
    @RequestMapping(value = "/getArchivesByCreateTime", method = RequestMethod.POST)
    public ResponseData getArchivesByCreateTime(@RequestBody ArchivesBo archivesBo){
        return articleConsumerService.getArchivesByCreateTime(archivesBo);
    }
}
