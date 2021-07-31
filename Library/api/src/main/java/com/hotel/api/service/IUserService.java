package com.hotel.api.service;

import com.library.model.User;

import java.util.List;

public interface IUserService {
    User register(User user);
    List<User> getAll();
    User findByUsername(String username);
    User findById(Integer userId);
    void delete(Integer userId);
}
