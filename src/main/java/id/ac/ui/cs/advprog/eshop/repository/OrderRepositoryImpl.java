package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderRepositoryImpl {
    private List<Order> orderData = new ArrayList<>();

    public Order save(Order order) { return null; }

    public Order findById(UUID orderId) { return null; }

    public List<Order> findAllByAuthor(String author) { return null; }
}