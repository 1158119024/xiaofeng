package com.xiaofeng.blogs.collect.controller;

import com.github.pagehelper.PageInfo;
import com.xiaofeng.base.httpformat.ResponseData;
import com.xiaofeng.blogs.collect.bo.CollectBo;
import com.xiaofeng.blogs.collect.dto.CollectDto;
import com.xiaofeng.blogs.collect.entity.CollectEntity;
import com.xiaofeng.blogs.collect.service.CollectService;
//import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.annotation.IsLogin;
import com.xiaofeng.checklogin.aop.AopUtils;
import com.xiaofeng.config.Constant;
import com.xiaofeng.utils.GetHtmlTitle;
import com.xiaofeng.utils.MD5Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * @auther: 晓枫
 * @date: 2018/10/16 17:00
 * @Description: 收藏Controller
 */
@RestController
@Log4j2
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @RequestMapping(value = "/getTool", method = RequestMethod.POST)
    public ResponseData getTool(HttpServletRequest request){
        Integer userId = AopUtils.getUserIdByToken(request);
        String path = "javascript:(function()%7Bvar%20description;var%20desString=%22%22;var%20metas=document.getElementsByTagName('meta');for(var%20x=0,y=metas.length;x%3Cy;x++)%7Bif(metas%5Bx%5D.name.toLowerCase()==%22description%22)%7Bdescription=metas%5Bx%5D;%7D%7Dif(description)%7BdesString=%22&amp;amp;description=%22+encodeURIComponent(description.content);%7Dvar%20win=window.open(%22" +
                Constant.webTool + "/" + userId +
                "?from=tool&amp;url=%22+encodeURIComponent(document.URL)+desString+%22&amp;title=%22+encodeURIComponent(document.title)+%22&amp;charset=%22+document.charset,'_blank');win.focus();%7D)();";
        return ResponseData.success(path);
    }

    @RequestMapping(value = "/getTitleByUrl", method = RequestMethod.POST)
    public ResponseData getTitleByUrl(String url) {
        return ResponseData.success(GetHtmlTitle.getTitleByUrl(url));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData add(@RequestBody CollectEntity collectEntity) {
        Integer result = collectService.add(collectEntity);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(collectEntity);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseData delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        if( id == null ){
            return ResponseData.fial("缺少参数！！");
        }
        Integer userId = AopUtils.getUserIdByToken(request);
        Integer result = collectService.delete(id, userId);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(id);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(@RequestBody CollectEntity collectEntity, HttpServletRequest request) {
        Integer id = collectEntity.getId();
        if( id == null ){
            return ResponseData.fial("缺少参数！！");
        }
        Integer userId = AopUtils.getUserIdByToken(request);
        collectEntity.setUserId(userId);
        Integer result = collectService.update(collectEntity);
        if( result == null || result == 0 ){
            return ResponseData.fial();
        }else{
            return ResponseData.success(collectEntity);
        }
    }

    @RequestMapping(value = "/getCollectsByCondition", method = RequestMethod.POST)
    public ResponseData getCollectsByCondition(@RequestBody CollectBo collectBo, HttpServletRequest request) {
        if( collectBo.getUserId() == null ){
            Integer userId = AopUtils.getUserIdByToken(request);
            collectBo.setUserId(userId);
        }
        collectBo.setTitle(collectBo.getTitle().trim());
        Integer pageNum = collectBo.getPageNum();
        Integer pageSize = collectBo.getPageSize();
        collectBo.setPageNum(pageNum == null || pageNum < 1 ? 1 : pageNum);
        collectBo.setPageSize(pageSize == null ? Constant.pageSize : pageSize);
        PageInfo<CollectDto> pageInfo = collectService.getCollectsByCondition(collectBo);
        return ResponseData.success(pageInfo);
    }


    @IsLogin
    @RequestMapping("/list")
    public ResponseData list(String token){
        List<CollectDto> list = collectService.list();
        return ResponseData.success(list);
    }

}
