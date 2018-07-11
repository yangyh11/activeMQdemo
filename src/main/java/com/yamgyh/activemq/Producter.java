package com.yamgyh.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangyh6
 * @version v1.0.0
 * @Description:
 * @date 2018/6/28
 * @time 15:02
 */
public class Producter {

    //生产者
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
//    private static final String BROKEN_URL=ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String BROKEN_URL="tcp://192.168.227.131:61616";

    AtomicInteger count=new AtomicInteger(0);
    ConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    ThreadLocal<MessageProducer> threadLocal=new ThreadLocal<MessageProducer>();

    private Queue queue;
    private MessageProducer producer;

    public void init(){
        try {
            connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
            connection=connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(true, Session.SESSION_TRANSACTED);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String queueName) {
        try {
            queue=session.createQueue(queueName);
            if(threadLocal.get()!=null){
                producer=threadLocal.get();
            }else {
                producer=session.createProducer(queue);
                threadLocal.set(producer);
//            }
            while (true){
                Thread.sleep(1000);
                int num=count.getAndIncrement();
                TextMessage msg=session.createTextMessage(Thread.currentThread().getName()+"正在生产东西,计数"+num);
                System.out.println(Thread.currentThread().getName()+"正在生产东西!，计数"+num);
                producer.send(msg);
                session.commit();
            }
        }
    } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }

//        try {
//            int num=count.getAndIncrement();
//            TextMessage msg=session.createTextMessage(Thread.currentThread().getName()+"正在生产东西,count"+num);
//            producer.send(msg);
//            session.commit();
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }

    }
}