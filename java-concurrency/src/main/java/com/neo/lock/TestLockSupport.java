package com.neo.lock;

import com.google.common.util.concurrent.Uninterruptibles;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * 实现一个容器，提供2个方法：add, size
 * 写2个线程，线程1添加10个元素到容器，
 * 线程2实现监控元素个数，当个数达到5，线程2给出提示并结束
 */
public class TestLockSupport {
    static Thread t2;
    static Thread t1;
    static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.println(Thread.currentThread().getName() +": "+ i);
                if (i == 4) {
                    Uninterruptibles.sleepUninterruptibly(Duration.of(1L, SECONDS));
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "T1");

        t2 = new Thread(() -> {
            while (list.size() != 5) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() +": listSize"+ list.size());
            }
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() +": "+ "over");
        }, "T2");

        t1.start();
        Uninterruptibles.sleepUninterruptibly(Duration.of(1L, SECONDS));
        t2.start();
    }
}
