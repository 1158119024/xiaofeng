package com.xiaofeng.blogs.user.entity;

import com.xiaofeng.base.entity.EntityBase;
import com.xiaofeng.blogs.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/20 16:07
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class UserEntity extends EntityBase<UserDto, UserEntity> implements Serializable {

    // 用户id
    private Integer id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 电话
    private String phone;

    // 账号是否启用
    private Boolean isEnable;
    // 创建时间
    private Date createTime;
}
