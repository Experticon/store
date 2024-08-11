package com.example.store.testServices;
import com.example.store.model.product;
import com.example.store.repository.productRep;
import com.example.store.service.productService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @class productServiceTests
 * Класс юнит тестов для проверки класса @productService
 */
@ExtendWith(MockitoExtension.class)
public class productServiceTests {

    @InjectMocks
    private productService ps;

    @Mock
    private productRep pRep;

    @Test
    public void test_GetProductById() {
        UUID productId = UUID.randomUUID();
        product product = new product();
        product.setId(productId);
        when(pRep.findById(productId)).thenReturn(Optional.of(product));

        product foundProduct = ps.getProductById(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId());
        verify(pRep, times(1)).findById(productId);
    }

    // обновить тест
    @Test
    public void test_createProduct() {
        product product = new product();
        product.setName("Test Product");
        when(pRep.save(product)).thenReturn(product);

        product createdProduct = ps.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        verify(pRep, times(1)).save(product);
    }
    @Test
    public void test_updateProduct() {
        UUID productId = UUID.randomUUID();
        product originalProduct = new product();
        originalProduct.setId(productId);
        originalProduct.setName("Original Product");
        originalProduct.setSku("123");

        when(pRep.save(originalProduct)).thenReturn(originalProduct);

        product savedProduct = ps.createProduct(originalProduct);

        assertNotNull(savedProduct);
        assertEquals("Original Product", savedProduct.getName());
        assertEquals("123", savedProduct.getSku());
        verify(pRep, times(1)).save(originalProduct);

        savedProduct.setName("Updated Product");
        savedProduct.setSku("456");

        when(pRep.save(savedProduct)).thenReturn(savedProduct);

        product updatedProduct = ps.updateProduct(savedProduct);

        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getName());
        assertNotEquals("Original Product", updatedProduct.getName());
        assertEquals("456", updatedProduct.getSku());
        assertNotEquals("123", updatedProduct.getSku());
        verify(pRep, times(2)).save(savedProduct);
    }
    @Test
    public void test_deleteProduct() {
        UUID productId = UUID.randomUUID();
        doNothing().when(pRep).deleteById(productId);
        ps.deleteProduct(productId);
        verify(pRep, times(1)).deleteById(productId);
    }
    @Test
    public void test_getAllProducts() {
        List<product> p = new ArrayList<>();
        product product1 = new product();
        p.add(product1);
        product product2 = new product();
        p.add(product2);

        when(pRep.findAll()).thenReturn(p);

        List<product> caught_pr =  ps.getAllProducts();

        assertNotNull(caught_pr);
        assertTrue(caught_pr.contains(product1));
        assertTrue(caught_pr.contains(product2));
        verify(pRep, times(1)).findAll();
    }
}
