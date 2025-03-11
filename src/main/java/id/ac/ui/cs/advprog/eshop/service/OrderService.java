package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService extends BaseModelService<Order> {
    Order updateStatus(UUID orderId, String status);
    List<Order> findAll(String author);
}
