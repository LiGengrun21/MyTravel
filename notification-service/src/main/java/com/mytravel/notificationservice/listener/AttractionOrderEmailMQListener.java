package com.mytravel.notificationservice.listener;

import com.alibaba.fastjson.JSONObject;
import com.mytravel.notificationservice.entity.dto.AttractionOrderEmailDto;
import com.mytravel.notificationservice.util.EmailSender;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;

/**
 * @author Li Gengrun
 * @date 2024/4/14 12:25
 */
@Component
@RabbitListener(queues = "attractionOrderEmail.queue")
public class AttractionOrderEmailMQListener {

    @RabbitHandler
    public void sendEmail(String jsonString) throws GeneralSecurityException {
        AttractionOrderEmailDto attractionOrderEmailDto= JSONObject.parseObject(jsonString, AttractionOrderEmailDto.class);
        System.out.println("attraction order info:"+attractionOrderEmailDto);
        String to=attractionOrderEmailDto.getEmail();
        String subject="MyTravel: Tourist Attraction Booking Confirmation";
        String content="Dear "+attractionOrderEmailDto.getUsername()+"\n\n" +
                "You have booked " + attractionOrderEmailDto.getVisitorsNumber()+" tickets in "+attractionOrderEmailDto.getAttractionName()+"\n\n" +
                "Total price is: "+attractionOrderEmailDto.getAmount()+"\n\n" +
                "Best regards, \n" +
                attractionOrderEmailDto.getAttractionName();
        System.out.println("content:"+content);
        EmailSender.sendEmail(to, subject, content);
    }
}
