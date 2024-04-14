package com.mytravel.orderservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.mytravel.orderservice.client.AttractionServiceClient;
import com.mytravel.orderservice.client.AuthServiceClient;
import com.mytravel.orderservice.client.pojo.Attraction;
import com.mytravel.orderservice.entity.AttractionOrder;
import com.mytravel.orderservice.entity.dto.AttractionOrderEmailDto;
import com.mytravel.orderservice.entity.dto.DetailedAttractionOrderDto;
import com.mytravel.orderservice.entity.dto.User;
import com.mytravel.orderservice.mapper.AttractionOrderMapper;
import com.mytravel.orderservice.service.AttractionOrderService;
import com.mytravel.orderservice.util.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/7 15:17
 */
@Service
public class AttractionOrderServiceImpl implements AttractionOrderService {

    @Autowired
    AttractionOrderMapper attractionOrderMapper;

    @Autowired
    AuthServiceClient authServiceClient;

    @Autowired
    AttractionServiceClient attractionServiceClient;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public Result getDetailedAttractionOrder(int orderId) throws Exception {
        AttractionOrder attractionOrder=attractionOrderMapper.selectById(orderId);
        if (attractionOrder==null){
            return Result.FAIL("The attraction order cannot be found!");
        }
        int userId=attractionOrder.getUserId();
        /**
         * open feign
         */
        User user=authServiceClient.getUserById(userId);
        /**
         * make the detailed attraction order
         */
        DetailedAttractionOrderDto detail=new DetailedAttractionOrderDto();
        detail.setUserId(userId);
        detail.setUsername(user.getUsername());
        detail.setEmail(user.getEmail());
        detail.setOrderId(orderId);
        detail.setPrice(attractionOrder.getPrice());
        detail.setCreatedAt(attractionOrder.getCreatedAt());
        detail.setUpdatedAt(attractionOrder.getUpdatedAt());
        detail.setOrderStatus(attractionOrder.getOrderStatus());
        detail.setAttractionId(attractionOrder.getAttractionId());
        detail.setVisitDate(attractionOrder.getVisitDate());
        detail.setVisitorsNumber(attractionOrder.getVisitorsNumber());
        return Result.SUCCESS(detail);
    }

    @Override
    public Result confirmAttractionOrder(int orderId) throws Exception {
        AttractionOrder attractionOrder=attractionOrderMapper.selectById(orderId);
        if (attractionOrder==null){
            return Result.FAIL("confirm attraction order failed because the order cannot be found.");
        }
        if (attractionOrder.getOrderStatus()==2){
            return Result.FAIL("Payment failed because the attraction order has already been paid.");
        }
        if (attractionOrder.getOrderStatus()==3){
            return Result.FAIL("Payment failed because the attraction order has been canceled.");
        }
        attractionOrder.setOrderStatus(2); // pay
        attractionOrderMapper.updateById(attractionOrder);
        /**
         * gather info from auth and attraction services
         */
        AttractionOrderEmailDto attractionOrderEmailDto=new AttractionOrderEmailDto();
        User user=authServiceClient.getUserById(attractionOrder.getUserId());
        Attraction attraction=attractionServiceClient.getAttractionById(attractionOrder.getAttractionId());
        attractionOrderEmailDto.setUsername(user.getUsername());
        attractionOrderEmailDto.setAttractionName(attraction.getName());
        attractionOrderEmailDto.setAmount(attractionOrder.getPrice());
        attractionOrderEmailDto.setVisitorsNumber(attractionOrder.getVisitorsNumber());
        attractionOrderEmailDto.setEmail(user.getEmail());
        /**
         * send attraction order info to notification service
         */
        String jsonString= JSON.toJSONString(attractionOrderEmailDto);
        rabbitTemplate.convertAndSend("email.exchange", "attractionOrderEmailRoutingKey", jsonString);
        return Result.SUCCESS(attractionOrder);
    }

    @Override
    public Result cancelAttractionOrder(int orderId) throws Exception {
        AttractionOrder attractionOrder=attractionOrderMapper.selectById(orderId);
        if (attractionOrder==null){
            return Result.FAIL("cancel attraction order failed because the order cannot be found.");
        }
        if (attractionOrder.getOrderStatus()==2){
            return Result.FAIL("Cancellation failed because the attraction order has been paid.");
        }
        if (attractionOrder.getOrderStatus()==3){
            return Result.FAIL("Cancellation failed because the attraction order has already been canceled.");
        }
        attractionOrder.setOrderStatus(3); // cancel
        attractionOrderMapper.updateById(attractionOrder);
        return Result.SUCCESS(attractionOrder);
    }
}
