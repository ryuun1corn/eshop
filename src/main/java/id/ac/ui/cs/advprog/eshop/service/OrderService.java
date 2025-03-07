package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.service.operations.Createable;
import id.ac.ui.cs.advprog.eshop.service.operations.Editable;
import id.ac.ui.cs.advprog.eshop.service.operations.Findable;

import java.util.List;
import java.util.UUID;

public interface OrderService extends Createable<Order>, Editable<Order>, Findable<Order> {
    Order updateStatus(UUID orderId, String status);
    List<Order> findAll(String author);
}
