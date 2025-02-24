package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }


    @Test
    void createProductPage_ShouldReturnCreateProductView() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void createProductPost_ShouldRedirectToList() throws Exception {
        Product product = new Product();
        when(productService.create(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/product/create")
                .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void listProductGet_ShouldReturnProductListView() throws Exception {
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void editProductPage_ShouldReturnEditProductView() throws Exception {
        Product mockProduct = new Product();
        UUID productId = UUID.randomUUID();
        mockProduct.setProductId(productId);
        when(productService.findOne(productId)).thenReturn(mockProduct);

        mockMvc.perform(get("/product/edit/" + productId))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("productToEdit"));
    }

    @Test
    void editProductPatch_ShouldRedirectToList() throws Exception {
        Product mockProduct = new Product();
        UUID productId = UUID.randomUUID();
        mockProduct.setProductId(productId);
        when(productService.edit(eq(productId), any(Product.class))).thenReturn(mockProduct);

        mockMvc.perform(patch("/product/edit/" + productId)
                .flashAttr("editedProduct", mockProduct))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).edit(eq(productId), any(Product.class));
    }

    @Test
    void deleteProductDelete_ShouldRedirectToList() throws Exception {
        when(productService.delete(any(UUID.class))).thenReturn(true);

        mockMvc.perform(delete("/product/delete/" + UUID.randomUUID()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete(any(UUID.class));
    }
}
