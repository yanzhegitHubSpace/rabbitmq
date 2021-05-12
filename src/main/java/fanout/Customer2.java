package fanout;

import com.rabbitmq.client.*;
import utils.RabbitMqUtils;

import java.io.IOException;

public class Customer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        // 绑定交换机
        channel.exchangeDeclare("logs","fanout");
        // 临时队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定交换机和队列
        channel.queueBind(queue,"logs","");
        // 消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("广播类型的消费者== 2 ==接受到的消息 ： " + new String(body));
            }
        });
    }

}
