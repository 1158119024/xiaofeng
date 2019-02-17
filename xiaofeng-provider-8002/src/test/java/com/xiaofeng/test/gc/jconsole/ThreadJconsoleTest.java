package com.xiaofeng.test.gc.jconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: 晓枫
 * @Date: 2019/2/12 16:34
 * @Description: jconsole 线程死循环和锁等待演示
 */
public class ThreadJconsoleTest {
    /**
     * 线程死循环演示
     */
    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true);
            }
        }, "testBusyThread");
        thread.start();
    }
    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("线程死循环演示开始（输入）：");
        System.out.println(br.readLine());
        createBusyThread();
        System.out.println("线程锁等待演示开始（输入）：");
        System.out.println(br.readLine());
        Object obj = new Object();
        createLockThread(obj);
    }
}
