package com.xiaofeng.test.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 晓枫
 * @Date: 2019/1/30 11:00
 * @Description: 常量区内存溢出：
 *
 * 前提：-XX:PermSize=10m -XX:MaxPermSize=10m -XX:+HeapDumpOnOutOfMemoryError
 * OutofMemoryError: PermGen space
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) throws InterruptedException {
//        testIntern();
        RuntimeConstantOOM();
    }

    public static void RuntimeConstantOOM(){
        // 用list保持着常量池引用，避免full　ＧＣ回收常量池行为
        List<String> list = new ArrayList<>();
        int i = 0;
        while(true){
            list.add(String.valueOf("增大占用啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦"+i++).intern());
        }
    }

    public static void testIntern(){
        String str1 = new StringBuffer("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuffer("ja").append("va").toString();
        System.out.println(str2.intern() == str2);


        String str3 = new StringBuffer("计算机").append("软件").toString();
        System.out.println(str3.intern() == str3);
    }
}
