package com.xiaofeng.article;

import com.alibaba.fastjson.JSON;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.blogs.article.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test(){
        ArrayList<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
        List<String> retainList = JSON.parseArray(JSON.toJSONString(list), String.class);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");


        retainList.retainAll(list2);
        System.out.println("交集："+retainList);

        list.removeAll(retainList);
        System.out.println("需要减少的："+list);

        list2.removeAll(retainList);
        System.out.println("需要增加的："+list2);
    }

    // 去掉html
    @Test
    public void stripHtmlTest(){
        String s = stripHtml("<p>123123</p>");
        System.out.println(s);
    }

    public String stripHtml(String content) {
// <p>段落替换为换行
        content = content.replaceAll("<p .*?>", "\r\n");
// <br><br/>替换为换行
        content = content.replaceAll("<br\\s*/?>", "\r\n");
// 去掉其它的<>之间的东西
        content = content.replaceAll("\\<.*?>", "");
// 还原HTML
// content = HTMLDecoder.decode(content);
        return content;
    }
}
