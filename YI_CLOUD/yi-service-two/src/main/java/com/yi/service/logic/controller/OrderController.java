package com.yi.service.logic.controller;


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
}
