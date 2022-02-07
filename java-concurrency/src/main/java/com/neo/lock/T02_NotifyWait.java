package com.neo.lock;

import com.google.common.util.concurrent.Uninterruptibles;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * 实现一个容器，提供2个方法：add, size
 * 写2个线程，线程1添加10个元素到容器，
 * 线程2实现监控元素个数，当个数达到5，线程2给出提示并结束
 */
public class T02_NotifyWait {
    static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.println(Thread.currentThread().getName() + ": " + i);
                if (i == 4) {
                    synchronized (lock) {
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    while (list.size() != 5) {
                        lock.wait();
                    }
                    System.out.println(Thread.currentThread().getName() +": listSize"+ list.size());
                    System.out.println(Thread.currentThread().getName() + ": " + "over");
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");

        t2.start();
        Uninterruptibles.sleepUninterruptibly(Duration.of(1L, SECONDS));
        t1.start();
    }
}
