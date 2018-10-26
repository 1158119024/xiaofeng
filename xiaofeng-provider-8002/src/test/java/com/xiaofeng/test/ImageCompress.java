package com.xiaofeng.test;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/24 12:27
 * @Description:
 */
public class ImageCompress {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("C:\\Users\\root\\Desktop\\腾讯云\\ymyhome.cn.certificate.jpg")
                .scale(1f)
                .outputQuality(0.5f)
                .toFile("C:\\Users\\root\\Desktop\\腾讯云\\ymyhome.cn.certificate2.jpg");
    }
}
