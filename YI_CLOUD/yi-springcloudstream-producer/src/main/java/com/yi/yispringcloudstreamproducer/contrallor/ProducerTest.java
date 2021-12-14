package com.yi.yispringcloudstreamproducer.contrallor;

import com.yi.yispringcloudstreamproducer.producer.RabbitmqProducer;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 */
@RestController
@RequestMapping
public class ProducerTest {

    @Autowired
    private RabbitmqProducer rabbitmqProducer;

    @RequestMapping("/sendMessageTest1")
    public void sendMessageTest1() throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            try {
                Map<String, Object> properties = new HashMap<String, Object>();
                properties.put("serial_number", "12345");
                properties.put("bank_number", "abc");
                properties.put("plat_send_time", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
                rabbitmqProducer.sendMessage("Hello, I am amqp sender num :" + i, properties);
            } catch (Exception e) {
                System.out.println("================================error================================");
                e.printStackTrace();
            }
        }
        Thread.sleep(50000);
    }
}
