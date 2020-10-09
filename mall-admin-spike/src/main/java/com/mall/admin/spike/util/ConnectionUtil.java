package com.mall.admin.spike.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * ConnectionUtil
 *
 * @Author BessCroft
 * @Date 2020/10/4 14:01
 */
public class ConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.在工厂对象中设置MQ的连接信息(ip,port,virtualhost,username,password)
        factory.setHost("47.96.11.185");
        factory.setPort(5672);
        factory.setVirtualHost("host1");
        factory.setUsername("ytao");
        factory.setPassword("admin123");
        //3.通过工厂对象获取与MQ的链接
        Connection connection = factory.newConnection();
        return connection;
    }

}
