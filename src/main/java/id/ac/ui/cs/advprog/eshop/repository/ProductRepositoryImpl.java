package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductId(UUID.randomUUID());
        productData.add(product);
        return product;
    }

    public Product edit(UUID productId, Product updatedProduct) {
        Product foundProduct = findOne(productId);
        if (foundProduct != null) {
            if (updatedProduct.getProductName() != null) {
                foundProduct.setProductName(updatedProduct.getProductName());
            }

            if (updatedProduct.getProductQuantity() != 0) {
                foundProduct.setProductQuantity(updatedProduct.getProductQuantity());
            }

            return foundProduct;
        }

        return null;
    }

    public boolean delete(UUID productId) {
        Product foundProduct = findOne(productId);

        if (foundProduct != null) {
            return productData.remove(foundProduct);
        }

        return false;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findOne(UUID productId) {
        for (Product existingProduct : productData)  {
            if (existingProduct.getProductId().equals(productId)) {
                return existingProduct;
            }
        }

        return null;
    }
}
