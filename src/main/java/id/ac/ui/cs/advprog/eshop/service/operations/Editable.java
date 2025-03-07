package id.ac.ui.cs.advprog.eshop.service.operations;

import java.util.UUID;

public interface Editable<T> {
    T edit(UUID modelId, T model);
}
