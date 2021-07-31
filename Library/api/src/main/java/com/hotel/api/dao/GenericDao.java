package com.hotel.api.dao;

import com.library.model.AEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface GenericDao<T extends AEntity>{
    void save (T entity);
    T getById(Integer id);
    List<T> getAll();
    void delete(T entity);
    void update(T entity);
}
