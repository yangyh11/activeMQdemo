package com.yamgyh.activemq;

/**
 * @author yangyh6
 * @version v1.0.0
 * @Description:
 * @date 2018/7/1
 * @time 15:18
 */
public class ConsumerThread implements Runnable{

    Consumer consumer=new Consumer();
    String queueName;

    public ConsumerThread(String queueName) {
        this.queueName = queueName;
    }

    public void run() {
        consumer.init();
        consumer.getMessage(queueName);
    }
}
