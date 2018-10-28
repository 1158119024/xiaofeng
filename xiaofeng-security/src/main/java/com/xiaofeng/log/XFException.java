package com.xiaofeng.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/26 23:18
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class XFException {

    private String exClass;
    private String exception;
    private String method;
    private String exceptionContent;
    private String params;
    private Date exTime;

}
