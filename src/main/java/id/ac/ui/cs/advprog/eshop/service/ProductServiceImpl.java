package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.BaseModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements BaseModelService<Product> {
    @Autowired
    private BaseModelRepository<Product> productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public Product edit(UUID productId, Product product) {
        return productRepository.edit(productId, product);
    }

    public boolean delete(UUID productId) {
        return productRepository.delete(productId);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findOne(UUID productId) {
        return productRepository.findOne(productId);
    }
}
