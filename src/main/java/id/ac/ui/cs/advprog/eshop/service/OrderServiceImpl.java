package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepositoryImpl orderRepository;

    @Override
    public Order create(Order order) {
        if (orderRepository.findById(order.getId()) == null) {
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
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            order.setStatus(status);
            return orderRepository.save(order);
        }

        throw new NoSuchElementException("Order not found");
    }

    @Override
    public List<Order> findAll(String author) {
        return orderRepository.findAllByAuthor(author);
    }

    @Override
    public Order findOne(UUID orderId) {
        return orderRepository.findById(orderId);
    }
}
