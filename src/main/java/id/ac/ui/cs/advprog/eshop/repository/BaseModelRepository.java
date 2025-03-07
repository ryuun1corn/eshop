package id.ac.ui.cs.advprog.eshop.repository;

import operations.Creatable;
import operations.Deletable;
import operations.Editable;

import java.util.Iterator;
import java.util.UUID;

public interface BaseModelRepository<T> extends Creatable<T>, Editable<T>, Deletable {
    Iterator<T> findAll();
    T findOne(UUID modelId);
}
