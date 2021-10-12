package com.yi.service.logic.service;

import com.yi.service.logic.entity.OrderVo;
import com.yi.service.logic.fallback.ProductClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 服务降级和断熔其实一样的 只是从不同的应用出发
 * 熔断：一般是某个服务挂掉引起的。被动降级。（生产者）
 * 降级：一般从整体微服务负荷考虑，主动降级。（消费者）
 */
@FeignClient( value = "two-service",fallback = ProductClientFallBack.class)
// 创建一个ProductClientFallBack类实现这个接口，并实现这个接口的所有方法，为了对每个方法做不同的响应错略
// 指定当前的接口是openfeign组件，value是调用的服务名
public interface OrderService {
    /**
     * 保存数据
     */
    @RequestMapping(value = "/two/two-service/saveOrder", method = RequestMethod.POST,consumes="application/json")
    void saveOrder(@RequestBody OrderVo request);

    /**
     * 查询数据
     */
    @RequestMapping(value = "/two/two-service/queryOrder", method = RequestMethod.POST,consumes="application/json")
    String queryOrder(@RequestBody OrderVo request);
}
