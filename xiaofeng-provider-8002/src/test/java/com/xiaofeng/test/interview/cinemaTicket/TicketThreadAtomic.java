package com.xiaofeng.test.interview.cinemaTicket;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: 晓枫
 * @Date: 2019/2/26 19:50
 * @Description:
 */
public class TicketThreadAtomic implements Runnable {
    private static AtomicInteger ticket = new AtomicInteger(10);
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);//模拟网络延时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            int num;
//            if((num = ticket.getAndDecrement()) > 0){
//                System.out.println(Thread.currentThread().getName()+" - 买了第"+num+"张票");
//            }else{
//                break;
//            }

            if(ticket.get() >= 0){
                try {
                    Thread.sleep(100);//处理业务
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" - 买了第"+ticket.getAndDecrement()+"张票");
            }else{
                break;
            }

        }
    }
}
