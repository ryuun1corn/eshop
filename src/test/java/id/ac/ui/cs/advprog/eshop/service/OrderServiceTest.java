package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepositoryImpl orderRepository;

    List<Order> orders;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"),
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order(UUID.fromString("7f9e15bb-4b15-42f4-aebc-c3af385fb078"),
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
    }

    @Test
    void testCreateOrder() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).save(order);

        Order result = orderService.create(order);
        verify(orderRepository, times(1)).save(order);
        assertEquals(order.getId(), result.getId());
    }

    @Test
    void testCreateOrderIfAlreadyExists() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getId());

        assertNull(orderService.create(order));
        verify(orderRepository, times(0)).save(order);
    }

    @Test
    void testUpdateStatus() {
        Order order = orders.get(1);
        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(), order.getAuthor(),
                OrderStatus.SUCCESS.getValue());
        doReturn(order).when(orderRepository).findById(order.getId());
        doReturn(newOrder).when(orderRepository).save(any(Order.class));

        Order result = orderService.updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());

        assertEquals(order.getId(), result.getId());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidStatus() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.updateStatus(order.getId(), "INVALID_STATUS");
        });

        verify(orderRepository, times(0)).save(any(Order.class));
    }

    @Test
    void testUpdateStatusInvalidOrderId() {
        Order order = orders.get(1);
        doReturn(null).when(orderRepository).findById(order.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());
        });

        verify(orderRepository, times(0)).save(any(Order.class));
    }

    @Test
    void testFindByIdIfIdFound() {
        Order order = orders.get(1);
        doReturn(order).when(orderRepository).findById(order.getId());

        Order result = orderService.findOne(order.getId());
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getOrderTime(), result.getOrderTime());
        assertEquals(order.getAuthor(), result.getAuthor());
        assertEquals(order.getStatus(), result.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Order order = orders.get(1);
        doReturn(null).when(orderRepository).findById(order.getId());

        assertNull(orderService.findOne(order.getId()));
    }

    @Test
    void testFindAllByAuthorIfAuthorCorrect() {
        Order order = orders.get(1);
        doReturn(orders).when(orderRepository).findAllByAuthor(order.getAuthor());

        List<Order> result = orderService.findAll(order.getAuthor());
        for (Order resultOrder : result) {
            assertEquals(order.getAuthor(), resultOrder.getAuthor());
        }

        assertEquals(orders.size(), result.size());
    }

    @Test
    void testFindAllByAuthorIfAllLowercase() {
        Order order = orders.get(1);
        doReturn(new ArrayList<Order>()).when(orderRepository)
                .findAllByAuthor(order.getAuthor().toLowerCase());

        List<Order> result = orderService.
                findAll(order.getAuthor().toLowerCase());

        assertTrue(result.isEmpty());
    }
}
