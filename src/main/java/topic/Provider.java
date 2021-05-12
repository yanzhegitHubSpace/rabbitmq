package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMqUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics","topic");

        String routeKey = "user.save.fanAll";
        channel.basicPublish("topics",routeKey,null,("这里是topic动态路由模型，routeKey : [ " + routeKey + " ]").getBytes());

        // 关闭资源
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
