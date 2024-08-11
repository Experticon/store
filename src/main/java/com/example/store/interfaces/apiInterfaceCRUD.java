package com.example.store.interfaces;

import java.util.List;
import java.util.UUID;

/**
 * @interface apiInterfaceCRUD
 * Содержит методы для создания, чтения, обновления и удаления сущностей.
 * На данный момент реализован во всех классах пакета /api.
 */
public interface apiInterfaceCRUD<T> {
    List<T> getAllEntities();
    T getEntityById(UUID id);
    T createEntity(T entity);
    T updateEntity(UUID id, T entity);
    void deleteEntity(UUID id);
}
