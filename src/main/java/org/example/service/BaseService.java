package org.example.service;

public interface BaseService <T>{
    void create(T entity);
    T get(int id);
}
