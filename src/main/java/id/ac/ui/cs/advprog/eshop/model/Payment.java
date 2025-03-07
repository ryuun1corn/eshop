package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    // I'm using UUID as the type for id because it's
    // more robust than using a string
    UUID id;
    String method;
    @Setter
    String status;
    Map<String, String> paymentData;
    Order order;

    public Payment(UUID id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
    }
}
