package com.hai.backend.service;

import com.hai.backend.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    List<OrderEntity> getAllOrders();

    OrderEntity saveOrder(OrderEntity order);

    List<OrderEntity> findAllByUserEntityId(int id);

    OrderEntity finById(int id);

    List<Object> analysticOrder();

}
