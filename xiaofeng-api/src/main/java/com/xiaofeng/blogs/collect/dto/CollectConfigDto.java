package com.xiaofeng.blogs.collect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 17:46
 * @Description: 收藏配置传输对象
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class CollectConfigDto {

    //用户id
    private Integer userId;
    // 是否公开收藏
    private Boolean isShowCollect;
    // 是否将收藏展示展示在首页
    private Boolean isShow;
    // 收藏背景色
    private String backgroundColor;
    //收藏别名
    private String alias;
}
