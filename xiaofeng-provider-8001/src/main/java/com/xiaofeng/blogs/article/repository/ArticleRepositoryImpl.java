package com.xiaofeng.blogs.article.repository;

import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 21:38
 * @Description:
 */
public class ArticleRepositoryImpl {

//    @Update("update xiaofeng_article set " +
//            "title=#{title}, categroyId=#{categroyId}, tagsId=#{tagsId}, content=#{content}, commentNum=#{commentNum}," +
//            "commentdNum=#{commentdNum}, isTop=#{isTop}, topGrade=#{topGrade}, isPrivate=#{isPrivate}, isPublish=#{isPublish}," +
//            "createTime=#{createTime} where id=#{id} and userId = #{userId}")
    public String update(final ArticleEntity articleEntity){
        return new SQL(){
            {
                UPDATE("xiaofeng_article");
                if (!StringUtils.isEmpty(articleEntity.getTitle())) {
                    SET("title=#{title}");
                }
                if (!StringUtils.isEmpty(articleEntity.getCategoryId())) {
                    SET("categroyId=#{categroyId}");
                }
                if (!StringUtils.isEmpty(articleEntity.getTagsId())) {
                    SET("tagsId=#{tagsId}");
                }
                if (!StringUtils.isEmpty(articleEntity.getContent())) {
                    SET("content=#{content}");
                }
                if (!StringUtils.isEmpty(articleEntity.getCommentNum())) {
                    SET("commentNum=#{commentNum}");
                }
                if (!StringUtils.isEmpty(articleEntity.getCommendNum())) {
                    SET("commendNum=#{commendNum}");
                }
                if (!StringUtils.isEmpty(articleEntity.getBrowseNum())) {
                    SET("browseNum=#{browseNum}");
                }
                if (!StringUtils.isEmpty(articleEntity.getIsTop())) {
                    SET("isTop=#{isTop}");
                }
                if (!StringUtils.isEmpty(articleEntity.getTopGrade())) {
                    SET("topGrade=#{topGrade}");
                }
                if (!StringUtils.isEmpty(articleEntity.getIsPrivate())) {
                    SET("isPrivate=#{isPrivate}");
                }
                if (!StringUtils.isEmpty(articleEntity.getState())) {
                    SET("state=#{state}");
                }
                if (!StringUtils.isEmpty(articleEntity.getCreateTime())) {
                    SET("createTime=#{createTime}");
                }
                WHERE("id=#{id}");
                WHERE("userId=#{userId}");
            }
        }.toString();
    }
    public String getArticlesByUserId(final ArticleBo articleBo){
        return new SQL() {
            {
                SELECT("*");
                FROM("xiaofeng_article");
                WHERE("userId = #{userId}");
                if( !StringUtils.isEmpty(articleBo.getTitle()) ){
                    WHERE("title like CONCAT('%',#{title},'%')");
                }
                if( !StringUtils.isEmpty(articleBo.getStartTime()) ){
                    WHERE("createTime >= #{startTime}");
                }
                if( !StringUtils.isEmpty(articleBo.getEndTime()) ){
                    WHERE("createTime <= #{endTime}");
                }
                if( !StringUtils.isEmpty(articleBo.getState()) ){
                    WHERE("state = #{state}");
                }
                if( !StringUtils.isEmpty(articleBo.getTagId()) ){
                    WHERE("tagsId like CONCAT('%',#{tagId},'%')");
                }
                ORDER_BY("createTime desc");
            }
        }.toString();
    }
}
