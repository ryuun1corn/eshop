package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepositoryImpl paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepositoryImpl();
        payments = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        Order order1 = new Order(UUID.fromString("13652556-012a-4c07-b546-54eb1396d79b"),
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order(UUID.fromString("7f9e15bb-4b15-42f4-aebc-c3af385fb078"),
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        Order order3 = new Order(UUID.fromString("e334ef40-9eff-4da8-9487-8ee697ecbf1e"),
                products, 1708570000L, "Bambang Sudrajat");
        orders.add(order3);

        Payment payment1 = new Payment(UUID.fromString("ced6d240-e9a7-4b5a-aae8-95be125c177c"),
                PaymentMethod.BANK_TRANSFER.getValue(),
                Map.of("bankName", "BCA", "referenceCode", "1234567890"), orders.get(0));
        Payment payment2 = new Payment(UUID.fromString("f3b3b3b3-1b3b-4b3b-8b3b-3b3b3b3b3b3b"),
                PaymentMethod.VOUCHER.getValue(),
                Map.of("voucherCode", "ESHOP1234ABC5678"), orders.get(1));
        Payment payment3 = new Payment(UUID.fromString("f3b3b3b3-1b3b-4b3b-8b3b-3b3b3b3b3b3b"),
                PaymentMethod.VOUCHER.getValue(),
                Map.of("voucherCode", "ESHOP1234ABC8765"), orders.get(2));

        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findOne(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getOrder(), findResult.getOrder());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        Payment newPayment = new Payment(payment.getId(), PaymentMethod.BANK_TRANSFER.getValue(),
            Map.of("bankName", "BCA", "referenceCode", "1234567890"), payment.getOrder());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findOne(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), findResult.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testSaveUpdateDifferentOrder() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);
        Payment newPayment = new Payment(payment.getId(), PaymentMethod.BANK_TRANSFER.getValue(),
                Map.of("bankName", "BCA", "referenceCode", "1234567890"), payments.get(2).getOrder());
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findOne(payments.get(1).getId());
        assertNull(result);
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getOrder(), findResult.getOrder());
        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testFindByIdIfFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findOne(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(payments.get(1).getOrder(), findResult.getOrder());
    }

    @Test
    void testFindByIdIfNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findOne(UUID.fromString("7f9e15bb-4b15-42f4-aebc-c3af385fb079"));
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(3, paymentList.size());
    }
}
