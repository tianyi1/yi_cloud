package com.yi.service.logic.service;


import com.yi.service.logic.entity.OrderVo;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
     String saveOrder(OrderVo value);

     /**
      * 查询订单信息
      * @param value
      * @return
      */
     String queryOrder(OrderVo value);
}
