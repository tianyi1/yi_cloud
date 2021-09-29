package com.yi.service.logic.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.yi.service.logic.entity.OrderVo;
import com.yi.service.logic.entity.UserVo;
import com.yi.service.logic.mapper.UserMapper;
import com.yi.service.logic.service.OrderService;
import com.yi.service.logic.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserVo> implements UserService {

    @Resource
    private UserMapper userMapper; //本地db操作

    @Resource
    private OrderService orderService;//远程B模块业务

    @Override
    @LcnTransaction //分布式事务注解
    @Transactional //本地事务注解
    public String saveUser(UserVo userVo){
        OrderVo order=new OrderVo();
        order.setId(userVo.getId());
        order.setUser_name(userVo.getUser_name());
        order.setOthers(userVo.getOthers());
        orderService.saveOrder(order);
        UserVo user=new UserVo();
        user.setId(userVo.getId());
        user.setUser_name(userVo.getUser_name());
        user.setOthers(userVo.getOthers());
        this.save(user);
//        double x=10/0;
        return "ok-B" + " > " + "ok-A";
    }
}
