package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;
import java.util.UUID;

public interface BaseModelRepository<T> {
    T create(T product);
    Iterator<T> findAll();
    T findOne(UUID productId);
    T edit(UUID productId, T updateT);
    boolean delete(UUID productId);
}
