package com.mytravel.notificationservice.listener;

import com.alibaba.fastjson.JSONObject;
import com.mytravel.notificationservice.entity.dto.HotelOrderEmailDto;
import com.mytravel.notificationservice.util.EmailSender;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;

/**
 * @author Li Gengrun
 * @date 2024/4/14 12:23
 */
@Component
@RabbitListener(queues = "hotelOrderEmail.queue")
public class HotelOrderEmailMQListener {

    @RabbitHandler
    public void sendEmail(String jsonString) throws GeneralSecurityException {
        HotelOrderEmailDto hotelOrderEmailDto= JSONObject.parseObject(jsonString, HotelOrderEmailDto.class);
        System.out.println("email info:"+hotelOrderEmailDto);
        String to=hotelOrderEmailDto.getEmail();
        String subject="MyTravel: Hotel Booking Confirmation";
        String content="Dear "+hotelOrderEmailDto.getUsername()+"\n\n" +
                "Thank you for choosing "+hotelOrderEmailDto.getHotelName()+". We have received your reservation request and we are delighted to serve you during your travels.\n\n" +
                "Your room number is: "+hotelOrderEmailDto.getRoomNumber()+"\n\n" +
                "Total price is: "+hotelOrderEmailDto.getAmount()+"\n\n" +
                "Best regards, \n" +
                hotelOrderEmailDto.getHotelName();
        System.out.println("content:"+content);
        EmailSender.sendEmail(to, subject, content);
    }
}
