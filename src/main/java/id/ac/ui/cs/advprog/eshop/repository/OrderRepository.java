package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import operations.Findable;

import java.util.List;


public interface OrderRepository extends Findable<Order> {
    Order save(Order order);
    List<Order> findAll(String author);
}
