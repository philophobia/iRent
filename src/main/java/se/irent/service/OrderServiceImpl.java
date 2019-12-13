package se.irent.service;

import se.irent.dao.OrderRepository;
import se.irent.entity.Order;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;

    @Override
    public Order findById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }
}
