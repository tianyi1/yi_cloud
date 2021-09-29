package com.yi.service.logic.service;

import com.yi.service.logic.entity.OrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient( name = "two-service")
public interface OrderService {
    /**
     * 保存数据
     */
    @RequestMapping(value = "/two/two-service/saveOrder", method = RequestMethod.POST,consumes="application/json")
    void saveOrder(@RequestBody OrderVo request);
}
