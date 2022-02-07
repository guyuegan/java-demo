package com.neo.refer;

import java.io.IOException;

public class T01_StrongRefer {
    public static void main(String[] args) throws IOException {
        T t = new T();
        t = null;
        System.gc();

        // 阻塞主线程
        System.in.read();
    }
}
