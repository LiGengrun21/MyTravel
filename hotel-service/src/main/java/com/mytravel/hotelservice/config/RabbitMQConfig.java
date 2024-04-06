package com.mytravel.hotelservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Li Gengrun
 * @date 2024/4/6 23:53
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    Queue queue() {
        Queue queue = new Queue("hotelReview.queue", false); //durable为false意味着队列中的消息在RabbitMQ服务重启后会丢失
        return queue;
    }

    @Bean
    DirectExchange exchange() {
        DirectExchange directExchange = new DirectExchange("review.exchange");
        return directExchange;
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("hotelReviewRoutingKey");
    }
}