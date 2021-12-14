package com.yi.yispringcloudstreamproducer.producer;

import com.yi.yispringcloudstreamproducer.config.Barista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author biehl
 *
 */
@EnableBinding(Barista.class) // 启动该绑定
@Service // 注入到Spring容器中
public class RabbitmqProducer {

    @Autowired // 将barista注入到spirng容器中
    private Barista barista;

    // 发送消息
    public String sendMessage(Object message, Map<String, Object> properties) throws Exception {
        try {
            // 设置消息头
            MessageHeaders mhs = new MessageHeaders(properties);
            System.out.println("信息头："+mhs);
            // 创建消息,使用消息和消息头创建消息。
            Message msg = MessageBuilder.createMessage(message, mhs);
            // 调用barista进行消息的发送。
            boolean sendStatus = barista.logoutput().send(msg);
            // 打印查看消息发送的情况
            System.out.println("========================sending========================");
            System.out.println("发送数据：" + message + ",sendStatus: " + sendStatus);
        } catch (Exception e) {
            System.out.println("========================error========================");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

}