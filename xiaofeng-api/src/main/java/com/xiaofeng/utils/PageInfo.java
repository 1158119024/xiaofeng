package com.xiaofeng.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/3 22:34
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class PageInfo {
    private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private boolean count;
    private Boolean reasonable;
    private Boolean pageSizeZero;
    private String countColumn;
    private String orderBy;
    private boolean orderByOnly;
    private int size;
}
