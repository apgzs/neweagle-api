package com.neweagle.api.module.sys.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: tjp
 * @description: 消息消费者
 * @date:create in 20:19 2018/3/23
 * @version: 1.0.0
 **/
//@Component  //启用mq的时候声明
@Slf4j
public class MqReceiver {

    @RabbitListener(queues = "msg.queue")
    public String processMessage1(String msg) {
        log.info(Thread.currentThread().getName() + " 接收到来自msg.queue队列的消息：" + msg);
        return msg.toUpperCase();
    }
}
