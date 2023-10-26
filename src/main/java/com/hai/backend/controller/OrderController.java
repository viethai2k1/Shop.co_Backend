package com.hai.backend.controller;

import com.hai.backend.entity.OrderEntity;
import com.hai.backend.modal.ApiReponse;
import com.hai.backend.modal.OrderStatus;
import com.hai.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("")
    public ApiReponse<List<OrderEntity>> getAllOrders() {
        try {
            return new ApiReponse<>(true, orderService.getAllOrders());
        }catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @PostMapping("/add")
    public ApiReponse<OrderEntity> addOrder(@RequestBody OrderEntity order) {
        try {
            return new ApiReponse<>(true, orderService.saveOrder(order));
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @GetMapping("/user/{id}")
    public ApiReponse<List<OrderEntity>> findAllByUserEntityId(@PathVariable int id) {
        try {
            return new ApiReponse<>(true, orderService.findAllByUserEntityId(id));
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @GetMapping("/{id}")
    public ApiReponse<OrderEntity> finById(@PathVariable int id) {
        try {
            return new ApiReponse<>(true, orderService.finById(id));
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/status/{id}")
    public ApiReponse<OrderEntity> updateStatusProduct(@PathVariable int id, @RequestBody OrderStatus orderStatus) {
        OrderEntity order = orderService.finById(id);
        order.setStatus(orderStatus.getStatus());
        orderService.saveOrder(order);
        return new ApiReponse<>(true, order);
    }

    @GetMapping("/analystic")
    public ApiReponse<List<Object>> analystic(){
        List<Object> analystic = orderService.analysticOrder();
        return new ApiReponse<>(true, analystic);
    }
}
