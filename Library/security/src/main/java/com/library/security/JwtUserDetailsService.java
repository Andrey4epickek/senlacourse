package com.library.security;

import com.hotel.api.service.IUserService;
import com.hotel.exceptions.DaoException;
import com.hotel.exceptions.ServiceException;
import com.library.model.User;
import com.library.security.jwt.JwtUser;
import com.library.security.jwt.JwtUserFactory;
import com.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER= LogManager.getLogger(ReaderService.class.getName());

    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            LOGGER.info(String.format("Loading of user by username"));
            User user =userService.findByUsername(username);

            if(user== null){
                throw new UsernameNotFoundException("User with username: "+username+" not found");
            }

            JwtUser jwtUser= JwtUserFactory.create(user);

            LOGGER.info(String.format("User: with username: %s successfully loaded",username));

            return jwtUser;
        } catch (DaoException e) {
            LOGGER.warn("Loading of user by username failed",e);
            throw new ServiceException("Loading of user by username failed",e);
        }

    }
}
