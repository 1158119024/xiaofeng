package com.xiaofeng.test.oom;

/**
 * @Auther: 晓枫
 * @Date: 2019/1/29 21:37
 * @Description: 栈溢出
 * StackOverflowError
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() throws InterruptedException {
        stackLength++;
        Thread.sleep(10);
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try{
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
