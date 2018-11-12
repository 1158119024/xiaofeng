package com.xiaofeng.blogs.tags.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.service.TagsService;
import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.aop.AopUtils;
import com.xiaofeng.config.Constant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 11:03
 * @Description:
 */
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    /**
     * 增加
     *  成功返回对应的实体
     * @param tagsEntity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @IsLogin
    public ResponseData add(@RequestBody TagsEntity tagsEntity, HttpServletRequest request){
        Integer userId = AopUtils.getUserIdByToken(request);
        tagsEntity.setUserId(userId);
        Integer result = tagsService.add(tagsEntity);
        if( result == null || result == 0 ){
            return ResponseData.addFial();
        }else{
            return ResponseData.success(tagsEntity);
        }
    }

    /**
     * 删除
     *  成功返回对应的id
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @IsLogin
    public ResponseData delete(@PathVariable("id") Integer id, HttpServletRequest request){
        Integer userId = AopUtils.getUserIdByToken(request);
        if( id == null || id == 0 ){
            return ResponseData.fial("缺少参数！！");
        }
        Integer result = tagsService.delete(id, userId);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(id);
        }
    }

    /**
     * 修改标签
     *  成功返回对应的实体
     * @param tagsEntity
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @IsLogin
    public ResponseData update(@RequestBody TagsEntity tagsEntity, HttpServletRequest request){
        Integer userId = AopUtils.getUserIdByToken(request);
        tagsEntity.setUserId(userId);
        Integer result = tagsService.update(tagsEntity);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(tagsEntity);
        }
    }

    /**
     * 自增
     *  成功返回对应的id
     * @param id
     * @return
     */
    @RequestMapping(value = "/incr", method = RequestMethod.POST)
    @IsLogin
    public ResponseData incr(@RequestBody Integer id, HttpServletRequest request){
        if( id == null || id == 0 ){
            return ResponseData.fial("缺少参数！！");
        }
        Integer userId = AopUtils.getUserIdByToken(request);
        Integer result = tagsService.incr(id, userId);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(id);
        }
    }

    /**
     * 根据id获取对应的标签实体
     *  成功返回对应的实体
     * @param id
     * @return
     */
    @RequestMapping(value = "/getTagById/{id}", method = RequestMethod.GET)
    public ResponseData getTagById(@PathVariable("id") Integer id){
        if( id == null || id == 0 ){
            return ResponseData.fial("缺少参数！！");
        }
        TagsEntity result = tagsService.getTagById(id);
        return ResponseData.success(result);
    }

    /**
     * 根据用户id获取对应的标签实体
     *  成功返回对应的实体List
     * @param map
     * @return
     */
    @RequestMapping(value = "/getTagsByUserId", method = RequestMethod.POST)
    @IsLogin
    public ResponseData getTagsByUserId(@RequestBody(required = false) Map<String, String> map, HttpServletRequest request){
        Integer userId = AopUtils.getUserIdByToken(request);
        String pageNumStr = map.get("pageNum");
        Integer pageNum = Integer.parseInt(StringUtils.isEmpty(pageNumStr) ? "1" : pageNumStr);
        String pageSizeStr = map.get("pageSize");
        Integer pageSize = Integer.parseInt(StringUtils.isEmpty(pageSizeStr) ? Constant.tagsPageSize.toString() : pageSizeStr);

        List<TagsEntity> result = tagsService.getTagsByUserId(userId, pageNum, pageSize, map.get("tagName"));
        PageInfo pageInfo = new PageInfo(result);
        return ResponseData.success(pageInfo);
    }
}
