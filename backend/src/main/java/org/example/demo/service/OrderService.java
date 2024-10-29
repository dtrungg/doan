package org.example.demo.service;

import org.example.demo.entity.Order;
import org.example.demo.model.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {

    void placeOrder(CreateOrderRequest request);

    List<Order> getList();

    List<Order> getOrderByUser(String username);
}
