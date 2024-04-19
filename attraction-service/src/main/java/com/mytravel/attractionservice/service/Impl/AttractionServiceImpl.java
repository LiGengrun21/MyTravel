package com.mytravel.attractionservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.mytravel.attractionservice.entity.Attraction;
import com.mytravel.attractionservice.entity.dto.AttractionOrderDto;
import com.mytravel.attractionservice.entity.dto.AttractionSearchDto;
import com.mytravel.attractionservice.mapper.AttractionMapper;
import com.mytravel.attractionservice.service.AttractionService;
import com.mytravel.attractionservice.util.Result;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Li Gengrun
 * @date 2024/4/7 13:01
 */
@Service
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private AttractionMapper attractionMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @CircuitBreaker(name = "attractionOrderCircuitBreaker", fallbackMethod = "sendMessageFallback")
    public void sendOrderMessage(String jsonString) {
        rabbitTemplate.convertAndSend("order.exchange", "attractionOrderRoutingKey", jsonString);
    }

    public void sendMessageFallback(Throwable t) {
        System.out.println("Failed to send attraction-order message, service might be down. " + t.getMessage());
    }

    @Override
    public Attraction getAttractionById(int attractionId) throws Exception {
        return attractionMapper.selectById(attractionId);
    }

    @Override
    public Result createAttraction(Attraction attraction) throws Exception {
        int result=attractionMapper.insert(attraction);
        if (result==0){
            return Result.FAIL("create a new tourist attraction failed!");
        }
        return Result.SUCCESS(attraction);
    }

    @Override
    public Result createOrder(AttractionOrderDto attractionOrderDto) throws Exception {

        /**
         * before creating order, check if there are enough tickets.
         */
        int attractionId= attractionOrderDto.getAttractionId();
        Attraction attraction=attractionMapper.selectById(attractionId);
        if (attraction==null){
            return Result.FAIL("The attraction doesn't exist.");
        }
        int remainingTickets=attraction.getTicketsAvailable();
        if (remainingTickets<attractionOrderDto.getVisitorsNumber()){
            return Result.FAIL("Tickets not enough.");
        }
        /**
         * If yes, send the order info to order-service.
         */
        String jsonString= JSON.toJSONString(attractionOrderDto);
        sendOrderMessage(jsonString);
        /**
         * Update available ticket number.
         */
        attraction.setTicketsAvailable(remainingTickets- attractionOrderDto.getVisitorsNumber());
        int result=attractionMapper.updateById(attraction);
        return Result.SUCCESS(result);
    }

    @Override
    public Result search(AttractionSearchDto attractionSearchDto) throws Exception {
        /**
         * 如果关键词为空，返回全部结果 （有点问题，但是暂时不管了）
         */
        if (attractionSearchDto.getKeyword()==""){
            return Result.SUCCESS(attractionMapper.selectList(null));
        }
        List<Attraction> searchResultList=attractionMapper.search(attractionSearchDto);
        return Result.SUCCESS(searchResultList);
    }
}
