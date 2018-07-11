package com.yamgyh.activemq;

/**
 * @author yangyh6
 * @version v1.0.0
 * @Description:
 * @date 2018/6/28
 * @time 15:03
 */
public class TestMQ {

    public static void main(String[] args) {
        producterThread producterThread=new producterThread("test2");
        new Thread(producterThread).start();
        new Thread(producterThread).start();
        new Thread(producterThread).start();
        new Thread(producterThread).start();
        new Thread(producterThread).start();
    }

}
