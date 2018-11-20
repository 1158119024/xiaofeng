package com.xiaofeng.blogs.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/19 18:32
 * @Description: 归档DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class ArchivesDto {

    private Integer userId;

    // 创建时间
    private String createTime;

    // 数量
    private String count;

    // 年
    private String year;
}
