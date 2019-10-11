package com.wangp.learn.microserviceorder.controller;

import com.wangp.learn.microserviceorder.entity.Order;
import com.wangp.learn.microserviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderById(orderId);
    }

    @GetMapping(value = "order2/{orderId}")
    public Order queryOrderById2(@PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderByIdx(orderId);
    }

    /**
     * Feign hystrix
     * @param orderId
     * @return
     */
    @GetMapping(value = "order3/{orderId}")
    public Order queryOrderById3(@PathVariable("orderId") String orderId) {
        return this.orderService.queryOrderById3(orderId);
    }
}
