package com.xiaofeng.test.interview.cinemaTicket;

/**
 * @Auther: 晓枫
 * @Date: 2019/2/26 19:19
 * @Description:
 */
public class CinemaTicket {

    public static void main(String[] args) {
        TicketThreadAtomic ticketThread = new TicketThreadAtomic();
//        TicketThread ticketThread = new TicketThread();
        new Thread(ticketThread, "一号窗口").start();
        new Thread(ticketThread, "二号窗口").start();
        new Thread(ticketThread, "三号窗口").start();
        new Thread(ticketThread, "四号窗口").start();
        new Thread(ticketThread, "五号窗口").start();
        new Thread(ticketThread, "六号窗口").start();
    }
}
