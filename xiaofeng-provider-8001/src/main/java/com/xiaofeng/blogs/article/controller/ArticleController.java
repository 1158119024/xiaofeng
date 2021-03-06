package com.xiaofeng.blogs.article.controller;

import com.github.pagehelper.PageInfo;
import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.article.bo.ArchivesBo;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.dto.ArticleDto;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.service.ArticleConsumerService;
import com.xiaofeng.blogs.article.service.ArticleService;
import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.aop.AopUtils;
import com.xiaofeng.config.Constant;
import com.xiaofeng.message.log.service.LogMessageHandleConsumerService;
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

    /**
     * 根据id删除
     * @param id
     * @param request
     * @return
     */
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

    /**
     * 整体修改
     * @param articleEntity
     * @param request
     * @return
     */
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

    /**
     * 修改文章状态
     * @param articleEntity
     * @param request
     * @return
     */
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

    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/getArticleById/{id}", method = RequestMethod.GET)
    public ResponseData getArticleById(@PathVariable("id") Integer id){
        ArticleDto articleDto = articleService.getArticleById(id);
        return ResponseData.success(articleDto);
    }

    /**
     * 以选中id为中，获取上中下三篇文章
     * @param articleBo
     * @return
     */
    @RequestMapping(value = "/getArticleAndPreAndNextById", method = RequestMethod.POST)
    public ResponseData getArticleAndPreAndNextById(@RequestBody ArticleBo articleBo){
        ArticleDto articleDto = articleService.getArticleAndPreAndNextById(articleBo.getId(), articleBo.getUserId());
        return ResponseData.success(articleDto);
    }

    /**
     * 后台根据条件查询文章
     * @param articleBo
     * @param request
     * @return
     */
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

    /**
     * 前台根据条件查询文章
     * @param articleBo
     * @return
     */
    @RequestMapping(value = "/getArticles", method = RequestMethod.POST)
    public ResponseData getArticles(@RequestBody ArticleBo articleBo){
        Integer pageNum = articleBo.getPageNum();
        Integer pageSize = articleBo.getPageSize();
        articleBo.setPageNum(pageNum == null || pageNum < 1 ? 1 : pageNum);
        articleBo.setPageSize(pageSize == null ? Constant.pageSize : pageSize);
        PageInfo<ArticleDto> pageInfo = articleService.getArticles(articleBo);
        return ResponseData.success(pageInfo);
    }

    /**
     * 根据创建时间归档查询
     * @return
     */
    @RequestMapping(value = "/getArchivesByCreateTime", method = RequestMethod.POST)
    public ResponseData getArchivesByCreateTime(@RequestBody ArchivesBo archivesBo){
        return ResponseData.success(articleService.getArchivesByCreateTime(archivesBo));
    }
}
