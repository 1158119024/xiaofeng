package com.xiaofeng.blogs.collect.bo;

import com.xiaofeng.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/20 16:51
 * @Description: 前端查询条件实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class CollectBo {

    private Integer id;
    // 用户id
    private Integer userId;
    // 名称
    private String title;
    // 标签
    private String tagId;
    // 当前页
    private Integer pageNum;
    // 每页大小
    private Integer pageSize;
}
