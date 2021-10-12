package com.yi.service.logic.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yi.service.logic.entity.OrderVo;
import com.yi.service.logic.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/two")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/two-service/saveOrder", method = RequestMethod.POST, consumes = "application/json")
    public String saveOrder(@RequestBody OrderVo order) {
        return orderService.saveOrder(order);
    }

    @RequestMapping(value = "/two-service/queryOrder", method = RequestMethod.POST, consumes = "application/json")
    @HystrixCommand(fallbackMethod = "testBreakFall" )//配置服务断熔
//    @HystrixCommand(defaultFallback = "testBreakFall" )//默认服务断熔
    public String queryOrder(@RequestBody OrderVo order) {
        return orderService.queryOrder(order);
    }

    // 触发熔断的方法
    //如果为每一个服务方法开发一个降级,对于我们来说,可能会出现大量的代码的冗余,不利于维护,这个时候就需要加入默认服务降级处理方法
    public String testBreakFall(OrderVo order){
        /**
         * 如果触发一定条件断路器会自动打开,过了一点时间正常之后又会关闭,那么断路器打开条件是什么呢?
         *
         */
        return "当前数据不合法: "+order.getUser_name();
    }
}
