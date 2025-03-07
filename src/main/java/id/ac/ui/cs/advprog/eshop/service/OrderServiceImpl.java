package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepositoryImpl orderRepository;

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Order edit(UUID orderId, Order order) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Order updateStatus(UUID orderId, String status) {
        return null;
    }

    @Override
    public List<Order> findAll(String author) {
        return List.of();
    }

    @Override
    public Order findOne(UUID orderId) {
        return null;
    }
}
