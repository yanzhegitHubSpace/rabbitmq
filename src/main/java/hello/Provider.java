package hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        // 创建连接mq工厂对象
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        // 设置连接rabbitmq主机
//        connectionFactory.setHost("172.30.1.59");
//        // 设置端口号
//        connectionFactory.setPort(5672);
//        // 设置连接哪个虚拟主机
//        connectionFactory.setVirtualHost("/ems");
//        // 设置用户名密码
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("123");
//        // 获取连接对象
//        Connection connection = connectionFactory.newConnection();
        // TODO 优化。使用工具类获取连接对象
        Connection connection = RabbitMqUtils.getConnection();
        // 获取连接中通道
        Channel channel = connection.createChannel();
        // 通道绑定对应的消息队列
        // 参数1：队列名称 如果队列不存在可以自动创建
        // 参数2：用来定义队列特性是否要持久化 true持久化 false 不持久化
        // 参数3：是否独占队列 true独占队列
        // 参数4：是否在消费完成后自动删除队列 true 自动删除
        // 参数5：附加参数
        channel.queueDeclare("hello", true,false,false,null);
        // 发布消息
        // 参数1： 交换机名称
        // 参数2： 队列名称
        // 参数3：传递消息的额外设置   MessageProperties.PERSISTENT_TEXT_PLAIN | 消息发送持久化，不会随因为重启而丢失
        // 参数4：消息的具体内容
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello rabbitmq 999".getBytes());
        // 关闭连接
        RabbitMqUtils.closeConnectionAndChanel(channel,connection);
    }
}
