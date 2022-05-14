package com.xiangpin.controller;

import com.xiangpin.util.ErrorUtil;
import com.xiangpin.util.ResponseUtil;
import com.xiangpin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



}
