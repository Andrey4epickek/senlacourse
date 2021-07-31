package com.library.dao;

import com.hotel.api.dao.GenericDao;
import com.library.model.AEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDao<T> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;

    public void save (T entity){
        entityManager.persist(entity);
    }

    public T getById(Integer id){
        return entityManager.find(getClazz(),id);
    }

    public List<T> getAll(){
        CriteriaBuilder builder=entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query=builder.createQuery(getClazz());
        Root<T> root=query.from(getClazz());
        CriteriaQuery<T> all=query.select(root);

        TypedQuery<T> allQuery=entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public void update(T entity){
        entityManager.merge(entity);
    }

    public void delete(T entity){
        entityManager.remove(entity);
    }


    protected abstract Class<T> getClazz();
}
