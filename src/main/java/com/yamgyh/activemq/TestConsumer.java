package com.yamgyh.activemq;

import java.sql.Connection;

/**
 * @author yangyh6
 * @version v1.0.0
 * @Description:
 * @date 2018/6/28
 * @time 15:03
 */
public class TestConsumer {

    public static void main(String[] args) {

        ConsumerThread consumerThread=new ConsumerThread("test2");
        new Thread(consumerThread).start();
        new Thread(consumerThread).start();
        new Thread(consumerThread).start();
        new Thread(consumerThread).start();
        new Thread(consumerThread).start();
    }
}
