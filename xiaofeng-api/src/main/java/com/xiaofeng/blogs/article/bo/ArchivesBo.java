package com.xiaofeng.blogs.article.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/19 18:37
 * @Description: 归档前端传输对象
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class ArchivesBo {

    private Integer userId;

    // 创建时间
    private String CreateTime;
}
