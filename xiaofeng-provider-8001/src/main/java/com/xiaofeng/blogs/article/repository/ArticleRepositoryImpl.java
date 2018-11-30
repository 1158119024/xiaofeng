package com.xiaofeng.blogs.article.repository;

import com.xiaofeng.blogs.article.bo.ArchivesBo;
import com.xiaofeng.blogs.article.bo.ArticleBo;
import com.xiaofeng.blogs.article.entity.ArticleEntity;
import com.xiaofeng.utils.MybatisUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 21:38
 * @Description:
 */
public class ArticleRepositoryImpl {

    public String add(final ArticleEntity articleEntity){
        List<String> excludeFields = new ArrayList<>();// 需要排除的字段
        excludeFields.add("createTime");
        excludeFields.add("updateTime");
        Map<String, String> add = MybatisUtils.add(articleEntity, excludeFields);
        return new SQL(){
            {
                INSERT_INTO("xiaofeng_article");
                VALUES(add.get("key"), add.get("value"));
                VALUES("createTime", "now()");
                VALUES("updateTime", "now()");
            }
        }.toString();
    }
    public String update(final ArticleEntity articleEntity){
        List<String> excludeFields = new ArrayList<>();// 需要排除的字段
        excludeFields.add("updateTime");
        String sets = MybatisUtils.update(articleEntity, excludeFields);
        return new SQL(){
            {
                UPDATE("xiaofeng_article");
                SET(sets);
                SET("updateTime=now()");
                WHERE("id=#{id}");
                WHERE("userId=#{userId}");
            }
        }.toString();
    }

    public String getArticleAndPreAndNextById(final @Param("id") Integer id, final @Param("userId") Integer userId){
        return "SELECT * FROM xiaofeng_article\n" +
                "WHERE id IN (\n" +
                "\tSELECT\n" +
                "            CASE\n" +
                "            WHEN SIGN(id - #{id}) > 0 THEN MIN(id)\n" +
                "            WHEN SIGN(id - #{id}) < 0 THEN MAX(id)\n" +
                "        ELSE\n" +
                "            id\n" +
                "        END AS id\n" +
                "        FROM\n" +
                "            xiaofeng_article\n" +
                "        WHERE userId = #{userId} and state=1 and isPrivate=0\n" +
                "       \n" +
                "        GROUP BY\n" +
                "            SIGN(id - #{id})\n" +
                "        ORDER BY\n" +
                "            SIGN(id - #{id}), updateTime desc\n" +
                "    )\n" +
                "ORDER BY\n" +
                "    id ASC;";
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
                if( StringUtils.isEmpty(articleBo.getState()) ){
                    WHERE("state = 1");
                }else{
                    WHERE("state = #{state}");
                }
                if( !StringUtils.isEmpty(articleBo.getTagId()) ){
                    WHERE("tagsId like CONCAT('%',#{tagId},'%')");
                }
                if( articleBo.getIsPrivate() == null ){
                    WHERE("isPrivate = false");
                }else{
                    WHERE("isPrivate = #{isPrivate}");
                }
                if( articleBo.getIsTop() != null ){
                    WHERE("isTop = #{isTop}");
                }
                if( !StringUtils.isEmpty(articleBo.getArchivesTime()) ){
                    WHERE("DATE_FORMAT(createTime, '%Y-%m') = #{archivesTime}");
                }
                ORDER_BY("topGrade DESC, updateTime DESC");
            }
        }.toString();
    }

    public String getArchivesByCreateTime(final ArchivesBo archivesBo){
        return new SQL(){
            {
                SELECT("COUNT(1) AS COUNT, DATE_FORMAT(createTime, '%Y-%m') AS createTime, DATE_FORMAT(createTime, '%Y') AS year");
                FROM("xiaofeng_article");
                WHERE("userId = #{userId}");
                GROUP_BY("DATE_FORMAT(createTime, '%Y-%m')");
            }
        }.toString();
    }

}
