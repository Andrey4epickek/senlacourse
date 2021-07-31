package com.hotel.api.dao;

import com.library.model.User;

public interface IUserDao extends GenericDao<User> {
    User findByUsername(String username);
}
