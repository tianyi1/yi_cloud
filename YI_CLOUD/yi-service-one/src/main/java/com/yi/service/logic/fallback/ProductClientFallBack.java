package com.yi.service.logic.fallback;

import com.yi.service.logic.entity.OrderVo;
import com.yi.service.logic.service.OrderService;
import org.springframework.stereotype.Component;

/**
 * 注意:如果服务端降级和客户端降级同时开启,要求服务端降级方法的返回值必须与客户端方法降级的返回值一致!!!
 */
@Component
public class ProductClientFallBack implements OrderService {
    @Override
    public void saveOrder(OrderVo request) {
        System.out.println("异常333");
    }

    @Override
    public String queryOrder(OrderVo request) {
        return "服务降级请求生效！";
    }
}
