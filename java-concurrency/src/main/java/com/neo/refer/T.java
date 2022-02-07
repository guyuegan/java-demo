package com.neo.refer;

public class T {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
