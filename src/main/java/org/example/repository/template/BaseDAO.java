package org.example.repository.template;

public interface BaseDAO<T>{
    void create(T entity);
    T get(int id);
}
