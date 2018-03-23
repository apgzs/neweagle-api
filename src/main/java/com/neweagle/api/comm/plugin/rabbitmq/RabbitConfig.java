package com.neweagle.api.comm.plugin.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tjp
 * @description: RabbitMq配置文件
 * @date:create in 20:12 2018/3/23
 * @version: 1.0.0
 **/
//@Configuration  //启用mq的时候声明
public class RabbitConfig {

    //声明队列
    @Bean
    public Queue msgQueue() {
        return new Queue("msg.queue", true); // true表示持久化该队列
    }

    //声明交互器
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    //绑定
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(msgQueue()).to(topicExchange()).with("key.1");
    }

}
