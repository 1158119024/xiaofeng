package com.xiaofeng.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.*;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/6 16:58
 * @Description:
 */
public class FileCompress {

    public static final Float qualityFine = 1f;
    public static final Float qualityGeneral = 0.7f;
    public static final Float qualityBad = 0.3f;

    public static void fileCompress(File source, File target, Float quality) throws IOException {
        Thumbnails.of(source).scale(1).outputQuality(quality).toFile(target);
    }

    public static void fileCompress(InputStream source, File target, Float quality) throws IOException {
        Thumbnails.of(source).scale(1).outputQuality(quality).toFile(target);
    }

    public static void fileCompress(InputStream source, OutputStream target, Float quality) throws IOException {
        Thumbnails.of(source).scale(1).outputQuality(quality).toOutputStream(target);
    }
}
