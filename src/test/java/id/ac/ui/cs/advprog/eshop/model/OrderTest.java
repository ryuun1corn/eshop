package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        Product product2 = new Product();
        product2.setId(UUID.fromString("a2c62328-4a37-4664-83c7-f32db8620155"));
        product2.setName("Sabun Cap Usep");
        product2.setQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"),
                    this.products, 1708560000L, "Safira Sudrajat");
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"),
                this.products, 1708560000L, "Safira Sudrajat");

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().getFirst().getName());
        assertEquals("Sabun Cap Usep", order.getProducts().getLast().getName());

        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", order.getId().toString());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"),
                this.products, 1708560000L, "Safira Sudrajat", OrderStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"),
                    this.products, 1708560000L, "Safira Sudrajat", "MEOW");
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"),
                this.products, 1708560000L, "Safira Sudrajat", OrderStatus.SUCCESS.getValue());
        order.setStatus(OrderStatus.CANCELLED.getValue());

        assertEquals(OrderStatus.CANCELLED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"),
                this.products, 1708560000L, "Safira Sudrajat", OrderStatus.SUCCESS.getValue());

        assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus("MEOW");
        });
    }
}
