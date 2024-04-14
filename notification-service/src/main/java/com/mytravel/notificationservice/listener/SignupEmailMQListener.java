package com.mytravel.notificationservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Li Gengrun
 * @date 2024/4/14 12:27
 */
@Component
@RabbitListener(queues = "signupEmail.queue")
public class SignupEmailMQListener {

    @RabbitHandler
    public void sendEmail(String jsonString){

    }
}
