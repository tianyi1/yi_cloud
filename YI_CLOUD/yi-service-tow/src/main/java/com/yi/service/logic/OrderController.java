package com.yi.service.logic;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/yi-service-tow/saveOrder", method = RequestMethod.POST, consumes = "application/json")
    public String saveOrder(@RequestBody OrderVo order) {
        return orderService.saveOrder(order);
    }
}
