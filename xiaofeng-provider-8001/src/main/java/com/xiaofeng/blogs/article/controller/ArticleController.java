package com.xiaofeng.blogs.article.controller;

import com.xiaofeng.base.httpformat.ResponseData;
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

    @RequestMapping(value = "/getArticleById/{id}", method = RequestMethod.GET)
    public ResponseData getArticleById(@PathVariable("id") Integer id){
        ArticleEntity entity = articleService.getArticleById(id);
        return ResponseData.success(entity);
    }

    @RequestMapping(value = "/getArticlesByUserId", method = RequestMethod.POST)
    public ResponseData getArticlesByUserId(@RequestBody(required = false) Map<String, String> map, HttpServletRequest request){
        Integer userId = AopUtils.getUserIdByToken(request);
        String pageNumStr = map.get("pageNum");
        Integer pageNum = Integer.parseInt(StringUtils.isEmpty(pageNumStr) ? "1" : pageNumStr);
        String pageSizeStr = map.get("pageSize");
        Integer pageSize = Integer.parseInt(StringUtils.isEmpty(pageSizeStr) ? Constant.pageSize.toString() : pageSizeStr);

        List<ArticleEntity> list = articleService.getArticlesByUserId(userId, pageNum, pageSize, map.get("title"));
        return ResponseData.success(list);
    }
}
