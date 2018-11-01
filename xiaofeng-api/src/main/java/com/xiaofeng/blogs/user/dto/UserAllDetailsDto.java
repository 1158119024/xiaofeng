package com.xiaofeng.blogs.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/1 15:42
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class UserAllDetailsDto implements Serializable {
    // 用户id
    private Integer id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 电话
    private String phone;
    // 别名
    private String aliasname;
    // 头像
    private String image;
    // 账号是否启用
    private Boolean isEnable;
    // 创建时间
    private Date createTime;
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
