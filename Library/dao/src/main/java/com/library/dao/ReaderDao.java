package com.library.dao;

import com.hotel.api.dao.IReaderDao;
import com.library.model.Reader;
import org.springframework.stereotype.Repository;

@Repository
public class ReaderDao extends AbstractDao<Reader> implements IReaderDao {

    @Override
    protected Class<Reader> getClazz() {
        return Reader.class;
    }
}
