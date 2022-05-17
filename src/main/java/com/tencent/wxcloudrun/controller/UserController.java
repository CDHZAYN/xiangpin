package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.UserService;
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
    public ApiResponse test(){
        return ApiResponse.error("fucked");
    }

    @GetMapping("/login")
    public ApiResponse login(@RequestHeader("x-wx-openid")String openID, @RequestHeader("x-wx-appid") String appID){
        return userService.login(openID, appID);
    }

}
