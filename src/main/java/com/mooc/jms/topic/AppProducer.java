package com.mooc.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author yangyh6
 * @version v1.0.0
 * @Description:
 * @date 2018/7/1
 * @time 16:24
 */
public class AppProducer {

    private static String url="tcp://192.168.227.131:61616";
    private static String topicName="topic_test";

    public static void main(String[] args) {

        try {
            ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
            Connection connection=connectionFactory.createConnection();
            connection.start();
            Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //目标
            Destination destination=session.createTopic(topicName);
            MessageProducer producer=session.createProducer(destination);
            for (int i = 0; i < 100; i++) {
                //创建消息
                TextMessage msg=session.createTextMessage("test"+i);
                producer.send(msg);
                System.out.println("发送消息"+msg.getText());
            }
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
