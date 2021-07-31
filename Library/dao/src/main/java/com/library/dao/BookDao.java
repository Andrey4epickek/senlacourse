package com.library.dao;

import com.hotel.api.dao.IBookDao;
import com.library.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends AbstractDao<Book> implements IBookDao {
    @Override
    protected Class<Book> getClazz() {
        return Book.class;
    }
}
