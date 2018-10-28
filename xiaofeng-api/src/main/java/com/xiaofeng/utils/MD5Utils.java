package com.xiaofeng.utils;

import java.security.MessageDigest;

/**
 * @Auther: 晓枫
 * @Date: 2018/10/26 18:13
 * @Description:
 */
public class MD5Utils {

    public static void main(String[] args) {
        String s = md5Password("123");
        System.out.println(s);
    }
    /**
     * 生成32位md5码
     * @param password
     * @return
     */
    public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}