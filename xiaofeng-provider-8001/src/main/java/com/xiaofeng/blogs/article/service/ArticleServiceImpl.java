package com.xiaofeng.blogs.article.service;

import com.github.pagehelper.PageHelper;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.repository.ArticleRepository;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 20:44
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    @Override
    public Integer add(ArticleEntity articleEntity) {
        return articleRepository.add(articleEntity);
    }

    @Override
    public Integer delete(Integer id, Integer userId) {
        return articleRepository.delete(id, userId);
    }

    @Override
    public Integer update(ArticleEntity articleEntity) {
        return articleRepository.update(articleEntity);
    }

    @Override
    public ArticleEntity getArticleById(Integer id) {
        return articleRepository.getArticleById(id);
    }

    @Override
    public List<ArticleEntity> getArticlesByUserId(Integer userId, Integer pageNum, Integer pageSize, String title) {
        List<ArticleEntity> list = PageHelper.startPage(pageNum, pageSize);
        articleRepository.getArticlesByUserId(userId, title);
        return list;
    }
}
