package se.irent.service;

import se.irent.entity.Order;

import java.util.List;

interface OrderService {
    Order findById(String id);
    List<Order> findAll();
    void deleteById(String id);
}
