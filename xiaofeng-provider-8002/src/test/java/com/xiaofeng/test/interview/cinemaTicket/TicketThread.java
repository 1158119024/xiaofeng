package com.xiaofeng.test.interview.cinemaTicket;

/**
 * @Auther: 晓枫
 * @Date: 2019/2/26 19:23
 * @Description:
 */
class TicketThread implements Runnable {
    private static int ticket=10;
    @Override
    public void run() {
        for(int i=1;i<=100;i++){
            synchronized(TicketThread.class){ //同步代码块+对象锁
                try {
                    Thread.sleep(100);//模拟网络延时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(ticket <= 0){
                    break;
                }
                System.out.println("----------------=-=-=");
                System.out.println(Thread.currentThread().getName()+" - 买了第"+ ticket-- +"张票");
            }
        }
    }
}
