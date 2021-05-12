package driect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMqUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        // 通道声明交换机 参数1：交换机名称  参数2：路由模式
        channel.exchangeDeclare("log_direct","direct");
        // 发送消息
        String routingKey = "error";
        channel.basicPublish("log_direct",routingKey,null,("这是direct模型发布的基于route key ：[" + routingKey + "] 发送的消息").getBytes());

        // 关闭资源
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
