package com.xiaofeng.blogs.article.service;

import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.dto.ArticleDto;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 20:41
 * @Description:
 */
public interface ArticleService {
    Integer add(ArticleEntity articleEntity);

    Integer delete(Integer id, Integer userId);

    Integer update(ArticleEntity articleEntity);

    ArticleDto getArticleById(Integer id);

    List<ArticleEntity> getArticlesByUserId(ArticleBo articleBo);
}
