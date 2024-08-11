package com.example.store.testServices;

import com.example.store.model.product;
import com.example.store.model.stock_mv;
import com.example.store.repository.productRep;
import com.example.store.repository.stock_mvRep;
import com.example.store.service.stock_mvService;
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
 * @class stock_mvServiceTests
 * Класс юнит тестов для проверки класса @stock_mvService
 */
@ExtendWith(MockitoExtension.class)
public class stock_mvServiceTests {

    @InjectMocks
    private stock_mvService st;

    @Mock
    private stock_mvRep stRep;

    @Mock
    private productRep pRep;

    @Test
    public void test_GetStock_mvById() {
        UUID stock_Id = UUID.randomUUID();
        stock_mv stock_mv = new stock_mv();
        stock_mv.setId(stock_Id);
        when(stRep.findById(stock_Id)).thenReturn(Optional.of(stock_mv));

        stock_mv foundStock = st.getStock_mvById(stock_Id);

        assertNotNull(foundStock);
        assertEquals(stock_Id, foundStock.getId());
        verify(stRep, times(1)).findById(stock_Id);
    }

    @Test
    public void test_createStock_mv() {
        product testproduct = new product();
        testproduct.setQuantity(10);
        stock_mv stock = new stock_mv();
        stock.setMovementType("IN");
        stock.setQuantity(30);
        stock.setProduct(testproduct);
        when(stRep.save(stock)).thenReturn(stock);

        stock_mv createdStock = st.createStock_mv(stock);

        assertNotNull(createdStock);
        assertEquals("IN", createdStock.getMovementType());
        assertEquals(40, createdStock.getProduct().getQuantity());
        assertEquals(testproduct, createdStock.getProduct());
        verify(stRep, times(1)).save(stock);
    }
    @Test
    public void test_updateStock_mv() {
        UUID stock_Id = UUID.randomUUID();
        product testproduct = new product();
        testproduct.setQuantity(10);

        stock_mv stock = new stock_mv();
        stock.setId(stock_Id);
        stock.setMovementType("IN");
        stock.setQuantity(30);
        stock.setProduct(testproduct);

        when(stRep.save(stock)).thenReturn(stock);

        // Симулируем сохранение оригинальной записи stock_mv
        stock_mv savedStock = st.createStock_mv(stock);

        assertNotNull(savedStock);
        assertEquals(stock_Id, savedStock.getId());
        assertEquals("IN", savedStock.getMovementType());
        assertEquals(40, savedStock.getProduct().getQuantity());
        assertEquals(testproduct, savedStock.getProduct());
        verify(stRep, times(1)).save(stock);

        // Подготавливаем обновленную запись stock_mv
        stock_mv updatedStock = new stock_mv();
        updatedStock.setId(savedStock.getId());
        updatedStock.setMovementType(savedStock.getMovementType());
        updatedStock.setQuantity(10);
        updatedStock.setProduct(savedStock.getProduct());

        // Симулируем поведение findById и save для обновленной записи stock_mv
        when(stRep.findById(stock_Id)).thenReturn(Optional.of(savedStock));
        when(stRep.save(updatedStock)).thenReturn(updatedStock);

        stock_mv newStock = st.updateStock_mv(updatedStock);

        assertNotNull(newStock);
        assertEquals(stock_Id, newStock.getId());
        assertEquals("IN", newStock.getMovementType());
        assertEquals(20, newStock.getProduct().getQuantity()); // Проверяем, что количество продукта обновлено корректно
        assertEquals(testproduct, newStock.getProduct());
        verify(stRep, times(1)).save(updatedStock);
    }

    @Test
    public void test_deleteStock_mv() {
        UUID stock_Id = UUID.randomUUID();
        doNothing().when(stRep).deleteById(stock_Id);
        st.deleteStock_mv(stock_Id);
        verify(stRep, times(1)).deleteById(stock_Id);
    }
    @Test
    public void test_getAllStock_mv() {
        List<stock_mv> p = new ArrayList<>();
        stock_mv stock1 = new stock_mv();
        p.add(stock1);
        stock_mv stock2 = new stock_mv();
        p.add(stock2);

        when(stRep.findAll()).thenReturn(p);

        List<stock_mv> caught_pr =  st.getAllStock_mv();

        assertNotNull(caught_pr);
        assertTrue(caught_pr.contains(stock1));
        assertTrue(caught_pr.contains(stock2));
        verify(stRep, times(1)).findAll();
    }
    
}
