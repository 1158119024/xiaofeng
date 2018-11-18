package com.xiaofeng.blogs.tags.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/17 22:07
 * @Description: 响应前端标签数据
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class TagsDto {
    // id
    private Integer id;
    // 别名
    private String aliasname;
    // 标签名称
    private String tagName;
    // 文章数量
    private Integer articleNum;
    // 创建时间
    private Date createTime;
}
