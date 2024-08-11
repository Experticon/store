package com.example.store.testServices;

import com.example.store.model.category;
import com.example.store.repository.categoryRep;
import com.example.store.service.categoryService;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * @class categoryServiceTests
 * Класс юнит тестов для проверки класса @categoryService
 */
@ExtendWith(MockitoExtension.class)
public class categoryServiceTests {
    @InjectMocks
    private categoryService cs;

    @Mock
    private categoryRep cRep;

    @Test
    public void test_GetCategoryById() {
        UUID categoryId = UUID.randomUUID();
        category category = new category();
        category.setId(categoryId);
        when(cRep.findById(categoryId)).thenReturn(Optional.of(category));

        category foundProduct = cs.getCategoryById(categoryId);

        assertNotNull(foundProduct);
        assertEquals(categoryId, foundProduct.getId());
        verify(cRep, times(1)).findById(categoryId);
    }

    @Test
    public void test_GetCategoryByName() {
        String categoryName = "New Ct";
        category category = new category();
        category.setName("New Ct");
        when(cRep.findByName(categoryName)).thenReturn(Optional.of(category));

        category foundCategory = cs.getCategoryByName(categoryName);

        assertNotNull(foundCategory);
        assertEquals(categoryName, foundCategory.getName());
        verify(cRep, times(1)).findByName(categoryName);
    }

    @Test
    public void test_createCategory() {
        category category = new category();
        category.setName("Test Product");
        when(cRep.save(category)).thenReturn(category);

        category createdProduct = cs.createCategory(category);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        verify(cRep, times(1)).save(category);
    }
    @Test
    public void test_updateCategory() {
        UUID productId = UUID.randomUUID();
        category originalCategory = new category();
        originalCategory.setId(productId);
        originalCategory.setName("Original Category");

        when(cRep.save(originalCategory)).thenReturn(originalCategory);

        category savedCategory = cs.createCategory(originalCategory);

        assertNotNull(savedCategory);
        assertEquals("Original Category", savedCategory.getName());
        verify(cRep, times(1)).save(originalCategory);

        savedCategory.setName("Updated Category");

        when(cRep.save(savedCategory)).thenReturn(savedCategory);

        category updatedCategory = cs.updateCategory(savedCategory);

        assertNotNull(updatedCategory);
        assertEquals("Updated Category", updatedCategory.getName());
        assertNotEquals("Original Category", updatedCategory.getName());
        verify(cRep, times(2)).save(savedCategory);
    }
    @Test
    public void test_deleteCategory() {
        UUID categoryId = UUID.randomUUID();
        doNothing().when(cRep).deleteById(categoryId);
        cs.deleteCategory(categoryId);
        verify(cRep, times(1)).deleteById(categoryId);
    }
    @Test
    public void test_getAllCategories() {
        List<category> p = new ArrayList<>();
        category category1 = new category();
        p.add(category1);
        category category2 = new category();
        p.add(category2);

        when(cRep.findAll()).thenReturn(p);

        List<category> caught_ct =  cs.getAllCategories();

        assertNotNull(caught_ct);
        assertTrue(caught_ct.contains(category1));
        assertTrue(caught_ct.contains(category2));
        verify(cRep, times(1)).findAll();
    }
}
