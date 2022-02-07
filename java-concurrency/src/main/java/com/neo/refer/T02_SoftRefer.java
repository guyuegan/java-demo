package com.neo.refer;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * 空间不足，回收软引用指向的对象
 */
public class T02_SoftRefer {
    public static void main(String[] args) throws IOException {
        SoftReference<T> softRefer = new SoftReference<>(new T());
//        softRefer = null;
        System.gc();

//        -Xms2m -Xmx2m
//        byte[] m1 = new byte[1024 * 1024];

        // 阻塞主线程
        System.in.read();
    }
}
