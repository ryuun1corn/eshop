package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public abstract class BaseModel {
    protected BaseModel() {
        // This constructor is intentionally empty.
    }

    private UUID id;
    private String name;
    private int quantity;
}
