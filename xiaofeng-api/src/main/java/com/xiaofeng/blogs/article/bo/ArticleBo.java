package com.xiaofeng.blogs.article.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/13 22:36
 * @Description: 接收前端请求数据
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class ArticleBo {

    private Integer id;
    // 用户id
    private Integer userId;
    // 文章标题
    private String title;
    // 当前页
    private Integer pageNum;
    // 每页大小
    private Integer pageSize;
    // 查询区间：开始时间
    private String startTime;
    // 查询区间： 结束时间
    private String endTime;
    // 发布状态：0：删除，1：发布，2：草稿
    private String state;
    // 标签id
    private String tagId;
    // 私密
    private Boolean isPrivate = false;
    // 置顶
    private Boolean isTop = true;

    // 按创建时间归档
    private String archivesTime;
}
