package com.yi.service.logic;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper; //本地db操作

    @Resource
    private OrderService orderService;//远程B模块业务

    @LcnTransaction //分布式事务注解
    @Transactional //本地事务注解
    public String saveUser(UserVo userVo){
        OrderVo order=new OrderVo();
        order.setId(userVo.getId());
        order.setUserName(userVo.getUserName());
        order.setOthers(userVo.getOthers());
        orderService.saveOrder(order);
        UserVo user=new UserVo();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setOthers(userVo.getOthers());
        userMapper.saveUser(user);
//        double x=10/0;
        return "ok-B" + " > " + "ok-A";
    }
}
