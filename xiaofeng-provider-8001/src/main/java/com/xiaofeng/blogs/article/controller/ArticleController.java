package com.xiaofeng.blogs.article.controller;

import com.github.pagehelper.PageInfo;
import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.dto.ArticleDto;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.service.ArticleConsumerService;
import com.xiaofeng.blogs.article.service.ArticleService;
import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.aop.AopUtils;
import com.xiaofeng.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 20:39
 * @Description: 文章
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     *  增加
     * @param articleEntity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @IsLogin
    public ResponseData add(@RequestBody ArticleEntity articleEntity, HttpServletRequest request){
        Integer userId = AopUtils.getUserIdByToken(request);
        articleEntity.setUserId(userId);
        Integer result = articleService.add(articleEntity);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(articleEntity);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @IsLogin
    public ResponseData delete(@PathVariable("id") Integer id, HttpServletRequest request){
        if( id == null || id == 0 ){
            return ResponseData.fial("缺少参数！！");
        }
        Integer userId = AopUtils.getUserIdByToken(request);
        Integer result = articleService.delete(id, userId);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(id);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @IsLogin
    public ResponseData update(@RequestBody ArticleEntity articleEntity, HttpServletRequest request){
        Integer id = articleEntity.getId();
        if( id == null ){
            return ResponseData.fial("缺少参数！！");
        }
        Integer userId = AopUtils.getUserIdByToken(request);
        articleEntity.setUserId(userId);
        Integer result = articleService.update(articleEntity);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(articleEntity);
        }
    }

    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    @IsLogin
    public ResponseData updateState(@RequestBody ArticleEntity articleEntity, HttpServletRequest request){
        Integer id = articleEntity.getId();
        if( id == null ){
            return ResponseData.fial("缺少参数！！");
        }
        Integer userId = AopUtils.getUserIdByToken(request);
        articleEntity.setUserId(userId);
        Integer result = articleService.updateState(articleEntity);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(articleEntity);
        }
    }

    @RequestMapping(value = "/getArticleById/{id}", method = RequestMethod.GET)
    public ResponseData getArticleById(@PathVariable("id") Integer id){
        ArticleDto articleDto = articleService.getArticleById(id);
        return ResponseData.success(articleDto);
    }

    @RequestMapping(value = "/getArticlesByUserId", method = RequestMethod.POST)
    @IsLogin
    public ResponseData getArticlesByUserId(@RequestBody ArticleBo articleBo, HttpServletRequest request){
        Integer userId = AopUtils.getUserIdByToken(request);
        Integer pageNum = articleBo.getPageNum();
        Integer pageSize = articleBo.getPageSize();
        articleBo.setPageNum(pageNum == null || pageNum < 1 ? 1 : pageNum);
        articleBo.setPageSize(pageSize == null ? Constant.pageSize : pageSize);
        articleBo.setUserId(userId);
        List<ArticleEntity> list = articleService.getArticlesByUserId(articleBo);
        PageInfo<ArticleEntity> pageInfo = new PageInfo<>(list);
        return ResponseData.success(pageInfo);
    }

    @RequestMapping(value = "/getArticles", method = RequestMethod.POST)
    public ResponseData getArticles(@RequestBody ArticleBo articleBo){
        Integer pageNum = articleBo.getPageNum();
        Integer pageSize = articleBo.getPageSize();
        articleBo.setPageNum(pageNum == null || pageNum < 1 ? 1 : pageNum);
        articleBo.setPageSize(pageSize == null ? Constant.pageSize : pageSize);
        PageInfo<ArticleDto> pageInfo = articleService.getArticles(articleBo);
        return ResponseData.success(pageInfo);
    }
}
