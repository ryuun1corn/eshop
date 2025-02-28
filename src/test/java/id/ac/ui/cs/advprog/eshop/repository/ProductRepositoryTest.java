package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepositoryImpl productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId(UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096"));
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(), savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindOneByIdIfEmpty() {
        Product foundProduct = productRepository.findOne(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        assertNull(foundProduct);
    }

    @Test
    void testFindOneByIdIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        product1 = productRepository.create(product1);
        product1.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));

        Product product2 = new Product();
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        product2 = productRepository.create(product2);
        product2.setId(UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096"));

        Product foundProduct1 = productRepository.findOne(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        Product foundProduct2 = productRepository.findOne(UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096"));
        assertEquals(foundProduct1.getId(), product1.getId());
        assertEquals(foundProduct2.getId(), product2.getId());
    }

    @Test
    void testEditAndFind() {
        Product newProduct = new Product();
        newProduct.setName("Sampo Cap Bambang");
        newProduct.setQuantity(100);
        productRepository.create(newProduct);

        UUID newProductId = newProduct.getId();
        Product editedProduct = new Product();
        editedProduct.setName("Sampo Cap Usep");
        editedProduct.setQuantity(50);
        Product editResultProduct = productRepository.edit(newProductId, editedProduct);
        assertNotEquals(null, editResultProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product foundProduct = productIterator.next();
        assertEquals(foundProduct.getId(), newProductId);
        assertEquals(foundProduct.getName(), editedProduct.getName());
        assertEquals(foundProduct.getQuantity(), editedProduct.getQuantity());
    }

    @Test
    void testEditNullProduct() {
        Product newProduct = new Product();
        newProduct.setName("Sampo Cap Bambang");
        newProduct.setQuantity(100);
        productRepository.create(newProduct);

        UUID newProductId = newProduct.getId();
        Product editedProduct = new Product();
        Product editResultProduct = productRepository.edit(newProductId, editedProduct);
        assertNotEquals(null, editResultProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product foundProduct = productIterator.next();
        assertEquals(foundProduct.getId(), newProductId);
        assertEquals(foundProduct.getName(), newProduct.getName());
        assertEquals(foundProduct.getQuantity(), newProduct.getQuantity());
    }

    @Test
    void testEditNotFound() {
        Product newProduct = new Product();
        newProduct.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        newProduct.setName("Sampo Cap Bambang");
        newProduct.setQuantity(100);
        productRepository.create(newProduct);

        UUID id = UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        Product editedProduct = new Product();
        editedProduct.setId(id);
        editedProduct.setName("Sampo Cap Usep");
        editedProduct.setQuantity(50);

        Product editResultProduct = productRepository.edit(id, editedProduct);
        assertNull(editResultProduct);
    }

    @Test
    void testDeleteAndFind() {
        Product newProduct = new Product();
        newProduct.setName("Sampo Cap Bambang");
        newProduct.setQuantity(100);
        productRepository.create(newProduct);

        productRepository.delete(newProduct.getId());

        Iterator<Product> allProducts = productRepository.findAll();
        assertFalse(allProducts.hasNext());
    }

    @Test
    void testDeleteNotFound() {
        Product newProduct = new Product();
        newProduct.setName("Sampo Cap Bambang");
        newProduct.setQuantity(100);
        newProduct.setId(UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6"));
        productRepository.create(newProduct);

        productRepository.delete(UUID.fromString("a0f9de46-90b1-437d-a0bf-d0821dde9096"));

        Iterator<Product> allProducts = productRepository.findAll();
        assertTrue(allProducts.hasNext());
    }
}
