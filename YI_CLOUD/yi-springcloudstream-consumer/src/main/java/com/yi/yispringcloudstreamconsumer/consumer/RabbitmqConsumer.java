package com.yi.yispringcloudstreamconsumer.consumer;


import com.rabbitmq.client.Channel;
import com.yi.yispringcloudstreamconsumer.config.Barista;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@EnableBinding(Barista.class) // 启动该绑定
@Service // 注入到Spring容器中
public class RabbitmqConsumer {

    /**
     * @StreamListener接收RabbitMQ的消息。指定了输入管道。
     *
     *
     *                                         接收消息
     * @param message
     * @throws Exception
     */
    @StreamListener(Barista.INPUT_CHANNEL)
    public void consumer(Message message) throws Exception {
        // 获取到具体的channel
        Channel channel = (com.rabbitmq.client.Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        // 获取到deliveryTag
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 消费消息
        System.out.println("类型："+deliveryTag);
        System.out.println("Input Stream 1 接受数据：" + message);
        System.out.println("消费完毕------------");
//        System.out.println(1/0);
        // 手动ack确认机制,false代表了1条一条进行消息签收
        channel.basicAck(deliveryTag, false);
    }

}
