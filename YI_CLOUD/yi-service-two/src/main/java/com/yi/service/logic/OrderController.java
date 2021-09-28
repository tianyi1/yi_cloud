package com.yi.service.logic;


import org.springframework.web.bind.annotation.*;

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
