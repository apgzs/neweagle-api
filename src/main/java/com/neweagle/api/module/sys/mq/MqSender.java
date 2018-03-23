package com.neweagle.api.module.sys.mq;

import java.util.UUID;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author: tjp
 * @description: 消息生产者
 * @date:create in 20:15 2018/3/23
 * @version: 1.0.0
 **/
//@Component   //启用mq的时候声明
@Slf4j
public class MqSender implements RabbitTemplate.ConfirmCallback, ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送成功:" + correlationData);
        } else {
            log.info("消息发送失败:" + cause);
        }

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info(message.getMessageProperties().getCorrelationIdString() + " 发送失败");

    }

    //发送消息，不需要实现任何接口，供外部调用。
    public void send(String msg){

        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        log.info("开始发送消息 : " + msg.toLowerCase());
        String response = rabbitTemplate.convertSendAndReceive("topicExchange", "key.1", msg, correlationId).toString();
        log.info("结束发送消息 : " + msg.toLowerCase());
        log.info("消费者响应 : " + response + " 消息处理完成");
    }
}

