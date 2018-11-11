package com.xiaofeng.config;

/**
 * @Auther: 晓枫
 * @Date: 2018/11/6 16:27
 * @Description:
 */
public class CosAndOssUser {

    // 腾讯云的Cos
    public static String domain = "https://myfile-1257553440.cos.ap-guangzhou.myqcloud.com/";
    public static String accessKey = "AKIDXxBzdA7ijYBlBPBIyStFRRGtLbkir5Vj";
    public static String secretKey = "PgokV4SeeSTL1UmuTrHZgbKrEPSJ0V4i";
    public static String regionName = "ap-guangzhou"; //地域名称
    public static String bucketName = "myfile-1257553440";//桶名 bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
    public static String keyPrefix = "upload/";

}
