package workquene;

import com.rabbitmq.client.*;
import utils.RabbitMqUtils;

import java.io.IOException;

public class Customer1 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        final Channel channel = connection.createChannel();
        // 绑定通道
        channel.queueDeclare("work",true,false,false,null);
        channel.basicQos(1);// 一次只能消费一个消息  通道中只能有一个消息
        // 消费消息
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("消费者== 1 ==获取到的消息："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }

}
