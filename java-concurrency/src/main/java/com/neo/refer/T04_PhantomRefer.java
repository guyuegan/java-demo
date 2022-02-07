package com.neo.refer;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * 如果只有弱引用，一旦gc, 弱引用指向的对象一定回收
 */
public class T04_PhantomRefer {
    public static void main(String[] args) throws IOException {
        ReferenceQueue<T> referQueue = new ReferenceQueue<>();
        T t = new T();
        PhantomReference<T> phantomRefer = new PhantomReference<>(t, referQueue);

        t = null;
        System.gc();
        while (true) {
                Reference<? extends T> poll = referQueue.poll();
                if (poll != null) {
                    System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll);
                }
            }

        // 阻塞主线程
    }

//    private static final List<Object> LIST = new LinkedList<>();
//    private static final ReferenceQueue<T> QUEUE = new ReferenceQueue<>();
//
//    public static void main(String[] args) {
//        PhantomReference<T> phantomReference = new PhantomReference<>(new T(), QUEUE);
//        new Thread(() -> {
//            while (true) {
//                LIST.add(new byte[1024 * 1024]);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    Thread.currentThread().interrupt();
//                }
//                System.out.println(phantomReference.get());
//            }
//        }).start();
//
//        new Thread(() -> {
//            while (true) {
//                Reference<? extends T> poll = QUEUE.poll();
//                if (poll != null) {
//                    System.out.println("--- 虚引用对象被jvm回收了 ---- " + poll);
//                }
//            }
//        }).start();
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }

}
