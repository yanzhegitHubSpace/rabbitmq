package driect;

import com.rabbitmq.client.*;
import utils.RabbitMqUtils;

import java.io.IOException;

public class Customer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();

        // 通道声明交换机以及交换机的类型
        channel.exchangeDeclare("log_direct","direct");

        // 获取创建一个临时队列
        String queue = channel.queueDeclare().getQueue();

        // 基于route key绑定队列和交换机
        channel.queueBind(queue,"log_direct","error");

        // 获取消费的消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("消费者== 1 ==" + new String(body));
            }
        });
    }
}
