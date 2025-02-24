package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;
import java.util.UUID;

public interface ProductRepository {
    Product create(Product product);
    Iterator<Product> findAll();
    Product findOne(UUID productId);
    Product edit(UUID productId, Product updateProduct);
    boolean delete(UUID productId);
}
