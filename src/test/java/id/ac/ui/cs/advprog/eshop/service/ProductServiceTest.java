package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.BaseModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private BaseModelRepository<Product> productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {}

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testEditProduct() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(productRepository.edit(productId, product)).thenReturn(product);

        Product editedProduct = productService.edit(productId, product);

        assertNotNull(editedProduct);
        assertEquals(product, editedProduct);
        verify(productRepository, times(1)).edit(productId, product);
    }

    @Test
    void testDeleteProduct() {
        UUID productId = UUID.randomUUID();
        when(productRepository.delete(productId)).thenReturn(true);

        boolean result = productService.delete(productId);

        assertTrue(result);
        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> foundProducts = productService.findAll();

        assertNotNull(foundProducts);
        assertEquals(2, foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindOneProduct() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        when(productRepository.findOne(productId)).thenReturn(product);

        Product foundProduct = productService.findOne(productId);

        assertNotNull(foundProduct);
        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findOne(productId);
    }
}
