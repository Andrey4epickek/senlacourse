package com.library.controller;

import com.hotel.api.service.IUserService;
import com.library.model.User;
import com.library.model.dto.AdminUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class AdminRestControllerV1 {

    private final IUserService userService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Integer id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}