package com.xiaofeng.test.gc;

/**
 * @Auther: 晓枫
 * @Date: 2019/1/31 21:57
 * @Description:
 *  1、对象可以在被GC时自我拯救
 *  2、这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统调用一次。
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        // 对象第一次成功拯救自己
//        SAVE_HOOK = null;
//        System.gc();
//        // 因为finalize方法优先级很低，所以暂停0.5秒以等待它。如果不等待的话，则本次SAVE_HOME为null
//        Thread.sleep(500);
//        if(SAVE_HOOK != null){
//            SAVE_HOOK.isAlive();
//        } else {
//            System.out.println("no, i am dead!");
//        }

        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead!");
        }
    }
}
