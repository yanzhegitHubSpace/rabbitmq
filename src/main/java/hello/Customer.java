package hello;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("172.30.1.59");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/ems");
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("123");
//        Connection connection = connectionFactory.newConnection();
        // TODO 优化。使用工具类获取连接对象
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,false,null);
        // 消费消息
        // 参数1： 消费哪个队列的消息 队列名称
        // 参数2：开始消息的自动确认机制
        // 参数3：消费消息时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override // 最后一个参数：消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("new String(body)" + new String(body));
            }
        });

    }

}
