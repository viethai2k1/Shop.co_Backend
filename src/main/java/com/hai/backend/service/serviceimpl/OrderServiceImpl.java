package com.hai.backend.service.serviceimpl;

import com.hai.backend.entity.OrderEntity;
import com.hai.backend.entity.OrderProductEntity;
import com.hai.backend.repository.OrderProductRepository;
import com.hai.backend.repository.OrderRepository;
import com.hai.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;
    @Override
    public List<OrderEntity> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    public OrderEntity saveOrder(OrderEntity order) {
        List<OrderProductEntity> products = order.getProducts();
        List<OrderProductEntity> productsSave = orderProductRepository.saveAll(products);
        order.setProducts(productsSave);

        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> findAllByUserEntityId(int id) {
        return orderRepository.findAllByUserEntityId(id);
    }

    @Override
    public OrderEntity finById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Object> analysticOrder() {
        return orderRepository.analysticOrder();
    }
}
