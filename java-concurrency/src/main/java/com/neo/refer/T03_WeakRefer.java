package com.neo.refer;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 如果只有弱引用，一旦gc, 弱引用指向的对象一定回收
 */
public class T03_WeakRefer {
    public static void main(String[] args) throws IOException {
//        onlyWeak();
        weakWithStrong();

        // 阻塞主线程
        System.in.read();
    }

    private static void onlyWeak() throws IOException {
        T t = new T();
        WeakReference<T> weakRefer = new WeakReference<>(t);
        t = null;
        System.gc();
    }

    private static void weakWithStrong() throws IOException {
        T t = new T();
        WeakReference<T> weakRefer = new WeakReference<>(t);
        System.gc();
    }
}
