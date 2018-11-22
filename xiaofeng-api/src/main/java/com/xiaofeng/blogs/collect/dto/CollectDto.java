package com.xiaofeng.blogs.collect.dto;

import com.xiaofeng.blogs.tags.entity.TagsEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 功能描述:
 * @auther: 晓枫
 * @date: 2018/10/16 17:00
 * @Description: 收藏DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class CollectDto {
    public Integer id;
    //用户Id
    private Integer userId;
    //名称
    private String title;
    //url
    private String url;
    //分类
    private Integer categoryId;
    //顶级分类
    private Integer categoryParentId;
    // 标签
    private String tagId;
    //是否展示在特殊页
    private Boolean isShow;
    //是否公开
    private Boolean isOpen;
    //创建时间
    private Date createTime;
    //采纳人数
    private Integer adoptCount;
    //浏览人数
    private Integer browseCount;
    // 描述
    private String readme;

    // 标签实体
    private TagsEntity tagsEntity;
}
