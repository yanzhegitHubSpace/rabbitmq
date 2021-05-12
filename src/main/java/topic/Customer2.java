package topic;

import com.rabbitmq.client.*;
import utils.RabbitMqUtils;

import java.io.IOException;

public class Customer2 {
    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("topics","topic");

        String queue = channel.queueDeclare().getQueue();

        // 绑定队列和交换机 TODO 动态通配符的形式 route key

        channel.queueBind(queue,"topics","user.#");

        // 消费消息
        channel.basicConsume(queue,true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者== 2 == 接受到的消息" + new String(body));
            }
        });
    }
}
