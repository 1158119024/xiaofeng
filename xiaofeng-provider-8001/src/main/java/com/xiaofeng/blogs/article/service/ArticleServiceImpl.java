package com.xiaofeng.blogs.article.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.dto.ArticleDto;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.repository.ArticleRepository;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.repository.TagsRepository;
import com.xiaofeng.blogs.tags.service.TagsService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 20:44
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private TagsService tagsService;

    @Override
    public Integer add(ArticleEntity articleEntity) {
        if( articleEntity.getIsTop() ){
            articleEntity.setTopGrade(1);
        }
        Integer userId = articleEntity.getUserId();
        String tagsId = articleEntity.getTagsId();
        tagsService.incrTagNum(tagsId, userId);
        return articleRepository.add(articleEntity);
    }

    @Override
    public Integer delete(Integer id, Integer userId) {
        ArticleEntity articleEntity = articleRepository.getArticleById(id);
        String tagsId = articleEntity.getTagsId();
        tagsService.decrTagNum(tagsId, userId);
        return articleRepository.delete(id, userId);
    }

    @Override
    public Integer update(ArticleEntity articleEntity) {
        if( articleEntity.getIsTop() != null ){
            if( articleEntity.getIsTop() ){
                articleEntity.setTopGrade(1);
            }else{
                articleEntity.setTopGrade(0);
            }
        }
        Integer userId = articleEntity.getUserId();
        List<String> decrList; // 要删的tagList
        List<String> incrList; // 要加的tagList
        List<String> retainList; // 交集
        ArticleEntity oldArticleEntity = articleRepository.getArticleById(articleEntity.getId());
        String oldTagsId = oldArticleEntity.getTagsId();
        String newTagsId = articleEntity.getTagsId();
        if( !StringUtils.isEmpty(oldTagsId) && !StringUtils.isEmpty(newTagsId) ){
            decrList = StringToList(oldTagsId);
            incrList = StringToList(newTagsId);
            decrList.remove("");
            incrList.remove("");
            retainList = JSON.parseArray(JSON.toJSONString(decrList), String.class); // 深克隆老的
            retainList.retainAll(incrList); // 取老的与新的list中的交集
            decrList.removeAll(retainList); // 移除交集，剩下就是要删除的
            incrList.removeAll(retainList); // 移除交集，剩下就是要增加的
            tagsService.updateTagNum(decrList, userId, "decr");
            tagsService.updateTagNum(incrList, userId, "incr");
        }else{
            if( StringUtils.isEmpty(oldTagsId) ){ // 老的等于空，把新的全部加1
                tagsService.incrTagNum(newTagsId, userId);
            }else if( StringUtils.isEmpty(newTagsId) ){ // 新的等于空，把老的全部减1
                tagsService.decrTagNum(oldTagsId, userId);
            }
        }
        return articleRepository.update(articleEntity);
    }

    /**
     *  修改一些状态字段，与整体修改分离开来
     * @param articleEntity
     * @return
     */
    @Override
    public Integer updateState(ArticleEntity articleEntity) {
        if( articleEntity.getIsTop() != null ){
            if( articleEntity.getIsTop() ){
                articleEntity.setTopGrade(1);
            }else{
                articleEntity.setTopGrade(0);
            }
        }
        return articleRepository.update(articleEntity);
    }

    @Override
    public ArticleDto getArticleById(Integer id) {
        ArticleEntity updateEntity = new ArticleEntity();

        ArticleEntity articleEntity = articleRepository.getArticleById(id);
        // 浏览数加1
        updateEntity.setBrowseNum(articleEntity.getBrowseNum() + 1).setId(id).setUserId(articleEntity.getUserId());
//        articleEntity.setBrowseNum(articleEntity.getBrowseNum() + 1);
        updateState(updateEntity);

        ArticleDto articleDto = articleEntity.entityToDto(ArticleDto.class);
        // 获取标签
        String tagsId = articleEntity.getTagsId();
        List<TagsEntity> tagList = tagsService.getTagEntityByTagsId(tagsId);
        articleDto.setTagList(tagList);
        return articleDto;
    }

    @Override
    public List<ArticleEntity> getArticlesByUserId(ArticleBo articleBo) {
        Integer pageNum = articleBo.getPageNum();
        Integer pageSize = articleBo.getPageSize();
        List<ArticleEntity> list = PageHelper.startPage(pageNum, pageSize);
        articleRepository.getArticlesByUserId(articleBo);
        return list;
    }

    @Override
    public PageInfo getArticles(ArticleBo articleBo) {
        Integer pageNum = articleBo.getPageNum();
        Integer pageSize = articleBo.getPageSize();
        List<ArticleEntity> list = PageHelper.startPage(pageNum, pageSize);
        articleRepository.getArticlesByUserId(articleBo);
        // 塞入page信息
        PageInfo pageInfo = new PageInfo(list);

        List<ArticleDto> articleList = new ArrayList<>();
        for(ArticleEntity articleEntity : list){
            ArticleDto articleDto = articleEntity.entityToDto(ArticleDto.class);
            String tagsId = articleEntity.getTagsId();
            List<TagsEntity> tagEntityByTagsId = tagsService.getTagEntityByTagsId(tagsId);
            articleDto.setTagList(tagEntityByTagsId);
            articleList.add(articleDto);
        }
        pageInfo.setList(articleList);
        return pageInfo;
    }

    // 数组去空
    public List<String> StringToList(String tagsId){
        List<String> list = new ArrayList<>();
        String[] split = tagsId.split(",");
        for( String tag : split ){
            if( !StringUtils.isEmpty(tag) ){
                list.add(tag);
            }
        }
        return list;
    }
}
