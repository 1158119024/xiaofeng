package com.xiaofeng.blogs.category.entity;

import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 10:36
 * @Description: 分类实体
 */
public class CategoryEntity {
    // id
    private Integer id;
    // 用户id
    private Integer userId;
    // 类名
    private String name;
    // 父类id
    private Integer parentId;
    // 是否是父类
    private Boolean isParent;
    // 图标
    private String icon;
    // 状态
    private Boolean state;
    // 创建时间
    private Date createTime;
    // 文章数量: 针对叶子节点
    private Integer articleNum;
}
