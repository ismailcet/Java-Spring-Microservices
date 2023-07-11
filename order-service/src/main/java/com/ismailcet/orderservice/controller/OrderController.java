package com.ismailcet.orderservice.controller;

import com.ismailcet.orderservice.dto.OrderRequest;
import com.ismailcet.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully ! ";
    }

    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return "Oops! Something went wrong, Please order after some time! ";
    }
}
