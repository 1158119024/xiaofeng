package com.xiaofeng.blogs.file.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/7 15:48
 * @Description: 图片文件dto
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Accessors(chain=true)
public class ImageFileDto {

    // 图片id
    private Integer id;
    // 用户id
    private Integer userId;
    // 图片的访问域名
    private String domain;
    // 图片eTag, cos文件的md5
    private String eTag;
    // 图片的key, cos文件的标识
    private String cosKey;
    // 图片名称
    private String fileName;
    // 图片类型
    private String type;
    // 图片大小
    private Long size;
    // 图片质量
    private Float quality;
    // 图片访问的地址
    private String imageUrl;
    // 创建时间
    private Date createTime;
}
