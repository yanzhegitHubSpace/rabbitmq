package utils;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtils {

    private static ConnectionFactory connectionFactory;

    // 类加载时，只执行一次
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.30.1.59");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }

    // 定提供连接对象的方法
    public static Connection getConnection() {

        try {
            // 重量级资源，类加载的时候就创建
//            ConnectionFactory connectionFactory = new ConnectionFactory();
            return connectionFactory.newConnection();
        } catch (Exception e) {
            return null;
        }
    }

    public static void closeConnectionAndChanel(Channel channel,Connection conn){

        try {
            if(channel != null) channel.close();
            if(conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
