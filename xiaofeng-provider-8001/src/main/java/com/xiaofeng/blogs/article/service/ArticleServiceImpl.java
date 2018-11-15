package com.xiaofeng.blogs.article.service;

import com.github.pagehelper.PageHelper;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.dto.ArticleDto;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.repository.ArticleRepository;
import com.xiaofeng.blogs.tags.entity.TagsEntity;
import com.xiaofeng.blogs.tags.repository.TagsRepository;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private TagsRepository tagsRepository;

    @Override
    public Integer add(ArticleEntity articleEntity) {
        if( articleEntity.getIsTop() ){
            articleEntity.setTopGrade(1);
        }
        return articleRepository.add(articleEntity);
    }

    @Override
    public Integer delete(Integer id, Integer userId) {
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
        return articleRepository.update(articleEntity);
    }

    @Override
    public ArticleDto getArticleById(Integer id) {
        ArticleEntity articleEntity = articleRepository.getArticleById(id);
        ArticleDto articleDto = articleEntity.entityToDto(ArticleDto.class);
        List<TagsEntity> tagList = new ArrayList<>();
        // 获取标签
        String tagsId = articleEntity.getTagsId();
        if( !StringUtils.isEmpty(tagsId) ){
            String[] split = tagsId.split(",");
            for( String tagId : split ){
                if( !StringUtils.isEmpty(tagId) ){
                    tagList.add(tagsRepository.getTagById(Integer.parseInt(tagId)));
                }
            }
        }
        articleDto.setTagList(tagList);
        return articleDto;
    }

    @Override
    public List<ArticleEntity> getArticlesByUserId(ArticleBo articleBo) {
        Integer pageNum = articleBo.getPageNum();
        Integer pageSize = articleBo.getPageSize();
        // 查询置顶
        List<ArticleEntity> list = PageHelper.startPage(pageNum, pageSize);
        articleRepository.getArticlesByUserId(articleBo);
        return list;
    }
}
