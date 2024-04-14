package com.mytravel.notificationservice.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Li Gengrun
 * @date 2024/4/14 12:12
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queue1() {
        Queue queue1 = new Queue("hotelOrderEmail.queue", false);
        return queue1;
    }

    @Bean
    Queue queue2() {
        Queue queue2 = new Queue("attractionOrderEmail.queue", false);
        return queue2;
    }

    @Bean
    Queue queue3() {
        Queue queue3 = new Queue("signupEmail.queue", false);
        return queue3;
    }

    @Bean
    DirectExchange exchange() {
        DirectExchange directExchange = new DirectExchange("email.exchange");
        return directExchange;
    }

    @Bean
    Binding binding1(Queue queue1, DirectExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with("hotelOrderEmailRoutingKey");
    }

    @Bean
    Binding binding2(Queue queue2, DirectExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with("attractionOrderEmailRoutingKey");
    }

    @Bean
    Binding binding3(Queue queue3, DirectExchange exchange) {
        return BindingBuilder.bind(queue3).to(exchange).with("signupEmailRoutingKey");
    }
}
