package com.xiaofeng.blogs.tags.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/17 22:05
 * @Description: 接收前端请求数据
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class TagsBo {
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

    // 当前页
    private Integer pageNum;
    // 每页大小
    private Integer pageSize;
}
