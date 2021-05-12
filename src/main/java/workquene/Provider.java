package workquene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMqUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        // 参数2： 不自动确认
        channel.queueDeclare("work", true,false,false,null);
        for(int i= 0;i < 20; i++){
            channel.basicPublish("","work",null,("work quene" + i).getBytes());
        }
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
