package com.mooc.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author yangyh6
 * @version v1.0.0
 * @Description:
 * @date 2018/7/1
 * @time 16:36
 */
public class AppConsumer {

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
            MessageConsumer consumer=session.createConsumer(destination);
            //监听器
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    TextMessage msg= (TextMessage) message;
                    try {
                        System.out.println("接收消息"+msg.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
//            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
