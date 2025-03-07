package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    @Override
    public Payment save(Payment payment) {
        boolean isOrderExist = isOrderExist(payment.getOrder().getId());
        int i = 0;
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getId().equals(payment.getId())) {
                if (!savedPayment.getOrder().equals(payment.getOrder())) {
                    return null;
                }

                paymentData.set(i, payment);
                return payment;
            }
            i++;
        }

        if (isOrderExist) {
            return null;
        }

        paymentData.add(payment);
        return payment;
    }

    @Override
    public List<Payment> findAll() {
        return paymentData;
    }

    @Override
    public Payment findOne(UUID modelId) {
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getId().equals(modelId)) {
                return savedPayment;
            }
        }

        return null;
    }

    @Override
    public Payment findOne(Order order) {
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getOrder().equals(order)) {
                return savedPayment;
            }
        }

        return null;
    }

    private boolean isOrderExist(UUID orderId) {
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getOrder().getId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }
}
