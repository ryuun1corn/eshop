package id.ac.ui.cs.advprog.eshop.model;

import enums.OrderStatus;
import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    // I'm using UUID as the type for id because it's
    // more robust than using a string
    UUID id;
    String method;
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(UUID id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;

        if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            this.method = method;
            this.paymentData = paymentData;
            this.order = order;
            ValidateVoucherMethod();
        } else if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            this.method = method;
            this.paymentData = paymentData;
            this.order = order;
            validateBankTransferMethod();
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }
    }

    private void ValidateVoucherMethod() {
        if (this.paymentData == null) {
            throw new IllegalArgumentException("Voucher payment must have voucher code");
        }

        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode != null && voucherCode.length() == 16 &&
                CountVoucherCodeDigits(voucherCode) == 8 && voucherCode.startsWith("ESHOP")) {
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    private void validateBankTransferMethod() {
        if (this.paymentData == null) {
            throw new IllegalArgumentException("Bank transfer payment must have bank name and reference code");
        }

        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            this.setStatus("REJECTED");
        } else {
            this.setStatus("SUCCESS");
        }
    }

    private int CountVoucherCodeDigits(String voucherCode) {
        int count = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS")) {
            this.status = status;
            this.order.setStatus(OrderStatus.SUCCESS.getValue());
        } else if (status.equals("REJECTED")) {
            this.status = status;
            this.order.setStatus(OrderStatus.FAILED.getValue());
        } else {
            throw new IllegalArgumentException("Invalid payment status");
        }
    }
}
