package com.mytravel.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
    Queue queue1() {
        Queue queue1=new Queue("hotelOrder.queue", false); //durable为false意味着队列中的消息在RabbitMQ服务重启后会丢失
        return queue1;
    }

    @Bean
    Queue queue2() {
        Queue queue2=new Queue("attractionOrder.queue", false);
        return queue2;
    }

    @Bean
    DirectExchange exchange() {
        DirectExchange directExchange=new DirectExchange("order.exchange");
        return directExchange;
    }

    /**
     * 绑定交换机和队列，所有带有hotelOrderRoutingKey的消息都被路由到这个队列
     * @param queue1
     * @param exchange
     * @return
     */
    @Bean
    Binding binding1(Queue queue1, DirectExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with("hotelOrderRoutingKey");
    }

    @Bean
    Binding binding2(Queue queue2, DirectExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with("attractionOrderRoutingKey");
    }
}
