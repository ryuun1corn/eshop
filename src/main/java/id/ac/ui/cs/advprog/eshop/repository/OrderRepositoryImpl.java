package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private List<Order> orderData = new ArrayList<>();

    public Order save(Order order) {
        int i = 0;
        for (Order savedOrder : orderData) {
            if (savedOrder.getId().equals(order.getId())) {
                orderData.set(i, order);
                return order;
            }
            i++;
        }

        orderData.add(order);
        return order;
    }


    public Order findOne(UUID orderId) {
        for (Order savedOrder : orderData) {
            if (savedOrder.getId().equals(orderId)) {
                return savedOrder;
            }
        }

        return null;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    public List<Order> findAll(String author) {
        List<Order> result = new ArrayList<>();

        for (Order savedOrder : orderData) {
            if (savedOrder.getAuthor().equals(author)) {
                result.add(savedOrder);
            }
        }

        return result;
    }
}