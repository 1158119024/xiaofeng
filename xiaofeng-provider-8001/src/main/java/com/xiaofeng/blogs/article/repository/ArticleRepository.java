package com.xiaofeng.blogs.article.repository;

import com.xiaofeng.blogs.article.entity.ArticleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 20:45
 * @Description:
 */
@Mapper
public interface ArticleRepository {

    @Insert("INSERT INTO " +
            "xiaofeng_article(" +
            "id, userId, title, categroyId, tagsId, content, commentNum, commendNum, isTop, topGrade, isPrivate, isPublish, createTime" +
            ") VALUE(" +
            "#{id}, #{userId}, #{title}, #{categroyId}, #{tagsId}, #{content}, #{commentNum}, #{commendNum}, #{isTop}, #{topGrade}, #{isPrivate}, #{isPublish}, #{createTime})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer add(ArticleEntity articleEntity);

    @Delete("DELETE FROM xiaofeng_article WHERE id = #{id} and userId = #{userId}")
    Integer delete(@Param("id") Integer id, @Param("userId") Integer userId);

    @UpdateProvider(type = ArticleRepositoryImpl.class, method = "update")
    Integer update(ArticleEntity articleEntity);

    @Select("select * from xiaofeng_article where id = #{id}")
    ArticleEntity getArticleById(Integer id);

//    @Select("select * from xiaofeng_article where userId = #{userId}")
    @SelectProvider(type = ArticleRepositoryImpl.class, method = "getArticlesByUserId")
    List<ArticleEntity> getArticlesByUserId(@Param("userId") Integer userId, @Param("title") String title);
}
