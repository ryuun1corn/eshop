package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        List<Product> products1 = new ArrayList<>();
        List<Product> products2 = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        Product product2 = new Product();
        product2.setId(UUID.fromString("a2c62328-4a37-4664-83c7-f32db8620155"));
        product2.setName("Sabun Cap Usep");
        product2.setQuantity(1);
        products1.add(product1);
        products1.add(product2);

        products2.add(product1);

        this.orders = new ArrayList<>();

        Order order1 = new Order(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"), products1,
                1708560000L, "Safira Sudrajat");
        Order order2 = new Order(UUID.fromString("a2c62328-4a37-4664-83c7-f32db8620155"), products2,
                1708570000L, "Bambang Sudrajat");

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(UUID.randomUUID(),
                    "OVO", null, this.orders.getFirst());
        });
    }

    @Test
    void testCreatePaymentValidVoucherCode() {
        Map<String, String> paymentData = Map.of("voucherCode", "ESHOP1234ABC5678");
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id,
                PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());

        assertEquals(id, payment.getId());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(this.orders.getFirst(), payment.getOrder());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testCreatePaymentNoVoucherCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(UUID.randomUUID(),
                    PaymentMethod.VOUCHER.getValue(), null, this.orders.getFirst());
        });
    }

    @Test
    void testCreatePaymentInvalidVoucherCodeLength() {
        Map<String, String> paymentData = Map.of("voucherCode", "ESHOP1234ABC5678XYZ");
        Payment payment = new Payment(UUID.randomUUID(),
                PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testCreatePaymentInvalidVoucherCodePrefix() {
        Map<String, String> paymentData = Map.of("voucherCode", "ZSHOP1234ABC5678");
        Payment payment = new Payment(UUID.randomUUID(),
                PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testCreatePaymentInvalidVoucherCodeNumericalCharactersAmount() {
        Map<String, String> paymentData = Map.of("voucherCode", "ESHOP1234ABCDEF");
        Payment payment = new Payment(UUID.randomUUID(),
                PaymentMethod.VOUCHER.getValue(), paymentData, this.orders.getFirst());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testCreatePaymentValidBankTransfer() {
        Map<String, String> paymentData = Map.of("bankName", "BCA", "referenceCode", "1234567890");
        UUID id = UUID.randomUUID();
        Payment payment = new Payment(id,
                PaymentMethod.BANK_TRANSFER.getValue(), paymentData, this.orders.getFirst());

        assertEquals("BCA", payment.getPaymentData().get("bankName"));
        assertEquals("1234567890", payment.getPaymentData().get("referenceCode"));
        assertEquals(id, payment.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(this.orders.getFirst(), payment.getOrder());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testCreatePaymentNoBankName() {
        Map<String, String> paymentData1 = Map.of("referenceCode", "1234567890");
        Payment payment1 = new Payment(UUID.randomUUID(),
                PaymentMethod.BANK_TRANSFER.getValue(), paymentData1, this.orders.getFirst());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment1.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment1.getOrder().getStatus());

        Map<String, String> paymentData2 = Map.of("bankName", "", "referenceCode", "1234567890");
        Payment payment2 = new Payment(UUID.randomUUID(),
                PaymentMethod.BANK_TRANSFER.getValue(), paymentData2, this.orders.getFirst());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment2.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment2.getOrder().getStatus());
    }

    @Test
    void testCreatePaymentNoReferenceCode() {
        Map<String, String> paymentData1 = Map.of("bankName", "BCA");
        Payment payment1 = new Payment(UUID.randomUUID(),
                PaymentMethod.BANK_TRANSFER.getValue(), paymentData1, this.orders.getFirst());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment1.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment1.getOrder().getStatus());

        Map<String, String> paymentData2 = Map.of("bankName", "BCA", "referenceCode", "");
        Payment payment2 = new Payment(UUID.randomUUID(),
                PaymentMethod.BANK_TRANSFER.getValue(), paymentData2, this.orders.getFirst());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment2.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment2.getOrder().getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment(UUID.randomUUID(),
                PaymentMethod.VOUCHER.getValue(), Map.of("voucherCode", "ESHOP1234ABC5678"), this.orders.getFirst());
        payment.setStatus(PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment(UUID.randomUUID(),
                PaymentMethod.VOUCHER.getValue(), Map.of("voucherCode", "ESHOP1234ABC56789"), this.orders.getFirst());
        payment.setStatus(PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment(UUID.randomUUID(),
                PaymentMethod.VOUCHER.getValue(), Map.of("voucherCode", "ESHOP1234ABC5678"), this.orders.getFirst());

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }
}
