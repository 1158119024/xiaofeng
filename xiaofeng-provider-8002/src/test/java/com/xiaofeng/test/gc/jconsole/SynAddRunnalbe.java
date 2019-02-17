package com.xiaofeng.test.gc.jconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: 晓枫
 * @Date: 2019/2/12 16:49
 * @Description: 死锁演示
 */
public class SynAddRunnalbe implements Runnable {

    int a, b;
    public SynAddRunnalbe(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)){
            synchronized (Integer.valueOf(b)){
                System.out.println(a + b);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("线程死循环演示开始（输入）：");
        System.out.println(br.readLine());
        for ( int i = 0; i < 100; i++ ){ // for循环多试几次增大死锁几率
            new Thread((new SynAddRunnalbe(1, 2))).start();
            new Thread((new SynAddRunnalbe(2, 1))).start();
        }
    }
}
