package com.library.dao;

import com.hotel.api.dao.IAcceptanceDao;
import com.library.model.Acceptance;
import org.springframework.stereotype.Repository;

@Repository
public class AcceptanceDao extends AbstractDao<Acceptance> implements IAcceptanceDao {
    @Override
    protected Class<Acceptance> getClazz() {
        return Acceptance.class;
    }
}
