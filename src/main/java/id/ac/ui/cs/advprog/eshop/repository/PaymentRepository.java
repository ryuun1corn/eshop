package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import operations.Findable;


public interface PaymentRepository extends Findable<Payment> {
    Payment save(Payment order);
    Payment findOne(Order order);
}