package com.mytravel.orderservice.service;

import com.mytravel.orderservice.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/7 15:14
 */
@Service
public interface AttractionOrderService {
    Result getDetailedAttractionOrder(int orderId) throws Exception;

    Result confirmAttractionOrder(int orderId) throws Exception;

    Result cancelAttractionOrder(int orderId) throws Exception;
}
