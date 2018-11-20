package com.xiaofeng.blogs.article.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/19 21:59
 * @Description: 对归档日期按年分类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class ArchivesKeyDto {
    private String year;

    private List<ArchivesDto> list = new ArrayList<>();
}
