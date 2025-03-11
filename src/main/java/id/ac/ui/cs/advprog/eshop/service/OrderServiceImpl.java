package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        if (orderRepository.findOne(order.getId()) == null) {
            return orderRepository.save(order);
        }

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
        Order order = orderRepository.findOne(orderId);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }

        throw new NoSuchElementException("Order not found");
    }

    @Override
    public List<Order> findAll(String author) {
        return orderRepository.findAll(author);
    }

    @Override
    public Order findOne(UUID orderId) {
        return orderRepository.findOne(orderId);
    }

    @Override
    public boolean delete(UUID modelId) {
        return false;
    }
}
