package com.hotel.api.dao;

import com.library.model.Role;


public interface IRoleDao extends GenericDao<Role> {
    Role findByName(String name);
}
