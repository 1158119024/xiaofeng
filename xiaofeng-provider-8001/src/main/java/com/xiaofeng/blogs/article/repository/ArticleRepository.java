package com.xiaofeng.blogs.article.repository;

import com.xiaofeng.blogs.article.bo.ArchivesBo;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.dto.ArchivesDto;
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

//    @Insert("INSERT INTO " +
//            "xiaofeng_article(" +
//            "id, userId, title, categoryId, tagsId, content, commentNum, commendNum, browseNum, isTop, topGrade, isPrivate, state, createTime" +
//            ") VALUE(" +
//            "#{id}, #{userId}, #{title}, #{categoryId}, #{tagsId}, #{content}, #{commentNum}, #{commendNum}, #{browseNum}, #{isTop}, #{topGrade}, #{isPrivate}, #{state}, now())")
    @InsertProvider(type = ArticleRepositoryImpl.class, method = "add")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    Integer add(ArticleEntity articleEntity);

    @Delete("DELETE FROM xiaofeng_article WHERE id = #{id} and userId = #{userId}")
    Integer delete(@Param("id") Integer id, @Param("userId") Integer userId);

    @UpdateProvider(type = ArticleRepositoryImpl.class, method = "update")
    Integer update(ArticleEntity articleEntity);

    @Update("update xiaofeng_article SET browseNum = browseNum + 1 where id=#{id}")
    Integer incrBrowseNum(Integer id);

    @Select("select * from xiaofeng_article where id = #{id}")
    ArticleEntity getArticleById(Integer id);

    // 获取文章的上中下篇
    @SelectProvider(type = ArticleRepositoryImpl.class, method = "getArticleAndPreAndNextById")
    List<ArticleEntity> getArticleAndPreAndNextById(@Param("id") Integer id, @Param("userId") Integer userId);

    //    @Select("select * from xiaofeng_article where userId = #{userId}")
    @SelectProvider(type = ArticleRepositoryImpl.class, method = "getArticlesByUserId")
    List<ArticleEntity> getArticlesByUserId(ArticleBo articleBo);

    @SelectProvider(type = ArticleRepositoryImpl.class, method = "getArchivesByCreateTime")
    List<ArchivesDto> getArchivesByCreateTime(ArchivesBo archivesBo);

    @Select("select count(1) from xiaofeng_article where userId = #{userId}")
    Integer getArticleCountByUserId(Integer userId);

}
