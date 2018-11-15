package com.xiaofeng.article;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/14 21:50
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    @Resource
    private ArticleRepository articleRepository;

    @Test
    public void add(){
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setContent("123123");
        Integer add = articleRepository.add(articleEntity);
        System.out.println(JSON.toJSONString(articleEntity));
    }

    @Test
    public void update(){
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(52).setUserId(1);
        articleEntity.setContent("+666666666666");
        Integer add = articleRepository.update(articleEntity);
        System.out.println(JSON.toJSONString(articleEntity));
    }
}
