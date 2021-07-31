package com.library.service;

import com.hotel.api.dao.IRoleDao;
import com.hotel.api.dao.IUserDao;
import com.hotel.api.service.IUserService;
import com.hotel.exceptions.DaoException;
import com.hotel.exceptions.ServiceException;
import com.library.model.Role;
import com.library.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private static final Logger LOGGER= LogManager.getLogger(UserService.class.getName());

    private final IUserDao userDao;
    private final IRoleDao roleDao;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    @Override
    public User register(User user) {
        try {
            LOGGER.info(String.format("Adding of user"));
            Role roleUser=roleDao.findByName("ROLE_USER");
            List<Role> userRoles=new ArrayList<>();
            userRoles.add(roleUser);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(userRoles);

            userDao.save(user);

            LOGGER.info(String.format("User successfully registered",user));

            return user;
        } catch (DaoException e) {
            LOGGER.warn("Adding of user failed",e);
            throw new ServiceException("Adding of user failed",e);
        }
    }

    @Override
    public List<User> getAll() {
        try {
            LOGGER.info(String.format("Getting of all users"));
            List<User> result=userDao.getAll();

            LOGGER.info(String.format("Users found",result.size()));

            return result;
        } catch (DaoException e) {
            LOGGER.warn("Getting of all users failed",e);
            throw new ServiceException("Getting of all users failed",e);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            LOGGER.info(String.format("Finding of user by username"));
            User result=userDao.findByUsername(username);

            LOGGER.info(String.format("User: %s found by username: %s",result,username));

            return result;
        } catch (DaoException e) {
            LOGGER.warn("Finding of user by username failed",e);
            throw new ServiceException("Finding of user by username failed",e);
        }
    }

    @Override
    public User findById(Integer userId) {
        try {
            LOGGER.info(String.format("Finding of user by id"));
            User result=userDao.getById(userId);

            if(result==null){
                LOGGER.info(String.format("No user found by id: {}",userId));
                return null;
            }

            LOGGER.info(String.format("User: %s found by id: %d",result,userId));

            return result;
        } catch (DaoException e) {
            LOGGER.warn("Finding of user by id failed",e);
            throw new ServiceException("Finding of user by id failed",e);
        }
    }

    @Override
    public void delete(Integer userId) {
        try {
            LOGGER.info(String.format("Deleting of user"));
            User user=userDao.getById(userId);
            userDao.delete(user);
            LOGGER.info(String.format("User with id: %d deleted",userId));

        } catch (DaoException e) {
            LOGGER.warn("Deleting of user failed",e);
            throw new ServiceException("Deleting of user failed",e);
        }
    }
}
