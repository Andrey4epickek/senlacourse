package com.library.dao;

import com.hotel.api.dao.IUserDao;
import com.library.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserDao  extends AbstractDao<User> implements IUserDao {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        User user= entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username LIKE :username",User.class)
                .setParameter("username",username)
                .getSingleResult();
        return user;
    }

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }
}
