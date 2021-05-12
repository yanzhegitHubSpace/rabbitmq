package workquene;

import com.rabbitmq.client.*;
import utils.RabbitMqUtils;

import java.io.IOException;

public class Customer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        final Channel channel = connection.createChannel();
        // 绑定通道
        channel.queueDeclare("work",true,false,false,null);
        channel.basicQos(1);// 一次只能消费一个消息  通道中只能有一个消息
        // 消费消息
        channel.basicConsume("work",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者== 2 ==获取到的消息："+new String(body));
                // 手动确认， 参数1： 手动确认标识 参数2： false 每次确认一个
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }

}
