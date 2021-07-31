package com.library.controller;

import com.hotel.api.service.IUserService;
import com.library.model.User;
import com.library.model.dto.AuthenticationRequestDto;
import com.library.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Log4j
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try{
            String username=requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(),requestDto.getPassword()));
            User user=userService.findByUsername(username);
            if(user==null){
                throw new UsernameNotFoundException("User with username: "+username+" not found");
            }
            String token=jwtTokenProvider.createToken(username,user.getRoles());
            Map<Object,Object> response=new HashMap<>();
            response.put("username",username);
            response.put("token",token);
            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
