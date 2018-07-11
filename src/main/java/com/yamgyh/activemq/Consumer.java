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
 * @time 15:01
 */
public class Consumer {
    //消费者

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
//    private static final String BROKEN_URL=ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String BROKEN_URL="tcp://192.168.227.131:61616";

    ConnectionFactory connectionFactory;
    ActiveMQConnection connection;
    Session session;

    private Queue queue;
    private MessageConsumer consumer;

    ThreadLocal<MessageConsumer> threadLocal=new ThreadLocal<MessageConsumer>();
    AtomicInteger count=new AtomicInteger(0);

    public void init(){
        try {
            connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
            connection= (ActiveMQConnection) connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(false,session.SESSION_TRANSACTED);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public void getMessage(String queueName){
        try {
            queue=session.createQueue(queueName);
            if(threadLocal.get()!=null){
                consumer=threadLocal.get();
            }else {
                consumer=session.createConsumer(queue);
                threadLocal.set(consumer);
            }
            while (true){
                Thread.sleep(1000);
                TextMessage msg= (TextMessage) consumer.receive();
                if (msg!=null){
                    msg.acknowledge();
                    System.out.println(Thread.currentThread().getName()+"我是消费者，正在消费msg"+msg.getText()+"--------消费者计数"+count.getAndIncrement());
                }else {
                    break;
                }

            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        try {
//            TextMessage msg= (TextMessage) consumer.receive();
//            if(msg!=null){
//                System.out.println("我是消费者，正在消费："+msg.getText()+"-----"+count.getAndIncrement());
//            }
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
    }

}
