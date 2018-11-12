package com.xiaofeng.blogs.article.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/11 18:03
 * @Description: 文章
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class ArticleEntity {
    // 文章id
    private Integer id;
    // 用户id
    private Integer userId;
    // 文章标题
    private String title;
    // 分类id
    private Integer categroyId;
    // 标签id，逗号分开
    private String tagsId;
    // 文章内容
    private String content;
    // 评论数
    private Integer commentNum = 0;
    // 点赞数
    private Integer commendNum = 0;
    // 是否置顶
    private Boolean isTop;
    // 置顶等级
    private Integer topGrade;
    // 是否是私密
    private Boolean isPrivate;
    // 是否发布
    private Boolean isPublish;
    // 创建时间
    private Date createTime;
}
