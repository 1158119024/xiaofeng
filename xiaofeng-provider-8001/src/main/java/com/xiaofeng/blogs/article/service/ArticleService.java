package com.xiaofeng.blogs.article.service;

import com.github.pagehelper.PageInfo;
import com.xiaofeng.blogs.article.bo.ArchivesBo;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.dto.ArchivesDto;
import com.xiaofeng.blogs.article.dto.ArchivesKeyDto;
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

    Integer updateState(ArticleEntity articleEntity);

    ArticleDto getArticleById(Integer id);

    ArticleDto getArticleAndPreAndNextById(Integer id, Integer userId);

    List<ArticleEntity> getArticlesByUserId(ArticleBo articleBo);

    PageInfo getArticles(ArticleBo articleBo);

    // 通过时间进行归档查询
    List<ArchivesKeyDto> getArchivesByCreateTime(ArchivesBo archivesBo);

}
