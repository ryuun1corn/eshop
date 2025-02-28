package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.UUID;

public interface BaseModelService<T> {
    T create(T model);
    T edit(UUID modelId, T model);
    boolean delete(UUID modelId);
    List<T> findAll();
    T findOne(UUID modelId);
}
