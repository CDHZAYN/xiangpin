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

    @GetMapping("/test")
    public ResponseUtil test(){
        return ResponseUtil.response(new ErrorUtil("0001","fuck"));
    }

    @GetMapping("/login")
    public ResponseUtil login(@RequestHeader("X-WX-OPENID")String openID, @RequestHeader("X-WX-ADDID") String appID){
        return userService.login(openID, appID);
    }

}
