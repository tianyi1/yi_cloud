package com.yi.service.logic.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.yi.service.logic.entity.OrderVo;
import com.yi.service.logic.mapper.OrderMapper;
import com.yi.service.logic.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderVo> implements OrderService {

    @Override
    @LcnTransaction //分布式事务注解
    @Transactional //本地事务注解
    public String saveOrder(OrderVo value){
         this.save(value);
        return "ok-B";
    }
}
