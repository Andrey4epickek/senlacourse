package com.library.dao;

import com.hotel.api.dao.IRoleDao;
import com.library.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;

    @Override
    public Role findByName(String name) {
        Role role=entityManager.createQuery(
                "SELECT r from Role r WHERE r.name LIKE :name", Role.class).
                setParameter("name",name).getSingleResult();
        return role;
    }

    @Override
    protected Class<Role> getClazz() {
        return Role.class;
    }
}
