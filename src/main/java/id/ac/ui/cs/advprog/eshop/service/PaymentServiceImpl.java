package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        if (paymentRepository.findOne(payment.getId()) == null &&
        paymentRepository.findOne(payment.getOrder()) == null) {
            return paymentRepository.save(payment);
        }

        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid status");
        }

        Payment paymentToUpdate = paymentRepository.findOne(payment.getId());
        if (paymentToUpdate != null) {
            paymentToUpdate.setStatus(status);
            return paymentRepository.save(paymentToUpdate);
        }

        return null;
    }

    @Override
    public Payment getPayment(UUID paymentId) {
        return paymentRepository.findOne(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
