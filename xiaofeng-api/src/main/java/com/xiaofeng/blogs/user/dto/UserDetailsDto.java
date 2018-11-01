package com.xiaofeng.blogs.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/1 11:57
 * @Description: 用户详情Dto
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class UserDetailsDto {

    private Integer id;
    // 用户id
    private Integer userId;
    // 作品数量
    private Integer productNum;
    // 收藏数量
    private Integer collectNum;
    // 评论数量
    private Integer commentNum;
    // 点赞数量
    private Integer commendNum;
    // 详情
    private String details;
}
