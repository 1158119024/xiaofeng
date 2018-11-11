package com.xiaofeng.blogs.tags.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 10:48
 * @Description: 标签实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class TagsEntity {
    // id
    private Integer id;
    // 用户id
    private Integer userId;
    // 标签名称
    private String tagName;
    // 文章数量
    private Integer articleNum;
    // 创建时间
    private Date createTime;
}
