package com.neo.thread;

import java.util.concurrent.Executors;

/**
 * 创建线程三种方式：
 *  1: 继承Thread
 *  2: 实现Runnable
 *  3: 线程池
 */
public class T01_CreateThread {
    public static void main(String[] args) {
        new MyThread().start();

        new Thread(() -> System.out.println("create method2: " + Thread.currentThread())).start();

        Executors.newCachedThreadPool().submit(() -> System.out.println("create method3: " + Thread.currentThread()));
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("create method1: " + Thread.currentThread());
        }
    }
}
