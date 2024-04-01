package com.mytravel.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * order-service是hotel-service和attraction-service的消费者，在消费者端定RabbitMQ的config，包括队列、交换机和绑定
 *
 * @author Li Gengrun
 * @date 2024/4/1 15:25
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queue() {
        Queue queue=new Queue("hotelOrder.queue", false); //durable为false意味着队列中的消息在RabbitMQ服务重启后会丢失
        return queue;
    }

    @Bean
    DirectExchange exchange() {
        DirectExchange directExchange=new DirectExchange("order.exchange");
        return directExchange;
    }

    /**
     * 绑定交换机和队列，所有带有hotelOrderRoutingKey的消息都被路由到这个队列
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("hotelOrderRoutingKey");
    }

    /**
     * 因为producer要发送object对象
     * @return Jackson2JsonMessageConverter
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
