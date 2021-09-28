package com.yi.service.logic;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;


    @LcnTransaction //分布式事务注解
    @Transactional //本地事务注解
    public String saveOrder(OrderVo value){
        orderMapper.saveData(value);
        return "ok-B";
    }
}
