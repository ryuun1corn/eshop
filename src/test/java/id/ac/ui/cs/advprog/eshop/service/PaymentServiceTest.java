package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepositoryImpl paymentRepository;

    List<Payment> payments;

    @BeforeEach
    void setUp() {
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
                Map.of("bankName", "BCA", "referenceCode", ""), orders.get(0));
        Payment payment2 = new Payment(UUID.fromString("f3b3b3b3-1b3b-4b3b-8b3b-3b3b3b3b3b3b"),
                PaymentMethod.VOUCHER.getValue(),
                Map.of("voucherCode", "ESHOP1234ABC5678"), orders.get(1));
        Payment payment3 = new Payment(UUID.fromString("b9fb4165-2925-47ca-8f33-e586d3e92c09"),
                PaymentMethod.VOUCHER.getValue(),
                Map.of("voucherCode", "ESHOP1234ABC8765"), orders.get(2));

        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
    }

    @Test
    void testAddPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(payment);

        Payment result = paymentService.addPayment(payment);
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(payment, result);
    }

    @Test
    void testAddPaymentIfAlreadyExists() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findOne(payment.getId());

        assertNull(paymentService.addPayment(payment));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testAddPaymentIfOrderAlreadyExists() {
        Payment payment = payments.get(1);
        doReturn(null).when(paymentRepository).findOne(payment.getId());
        doReturn(payment).when(paymentRepository).findOne(payment.getOrder());

        assertNull(paymentService.addPayment(payment));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = payments.getFirst();
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(),
                payment.getPaymentData(), payment.getOrder());
        newPayment.setStatus(PaymentStatus.SUCCESS.getValue());

        doReturn(payment).when(paymentRepository).findOne(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(result.getId(), payment.getId());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = payments.get(1);
        Payment newPayment = new Payment(payment.getId(), payment.getMethod(),
                payment.getPaymentData(), payment.getOrder());
        newPayment.setStatus(PaymentStatus.REJECTED.getValue());

        doReturn(payment).when(paymentRepository).findOne(payment.getId());
        doReturn(newPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        verify(paymentRepository, times(1)).save(payment);
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(result.getId(), payment.getId());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Payment payment = payments.get(1);

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payment, "INVALID_STATUS");
        });

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testSetStatusInvalidPaymentId() {
        Payment payment = payments.get(1);
        doReturn(null).when(paymentRepository).findOne(payment.getId());

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertNull(result);
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findOne(payment.getId());

        assertEquals(payment, paymentService.getPayment(payment.getId()));
    }

    @Test
    void testGetPaymentIfNotFound() {
        Payment payment = payments.get(1);
        doReturn(null).when(paymentRepository).findOne(payment.getId());

        assertNull(paymentService.getPayment(payment.getId()));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        assertEquals(payments, paymentService.getAllPayments());
    }
}
