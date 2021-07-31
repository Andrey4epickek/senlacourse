package com.library.dao;

import com.hotel.api.dao.IIssuanceDao;
import com.library.model.Issuance;
import org.springframework.stereotype.Repository;

@Repository
public class IssuanceDao extends AbstractDao<Issuance> implements IIssuanceDao {
    @Override
    protected Class<Issuance> getClazz() {
        return Issuance.class;
    }
}
