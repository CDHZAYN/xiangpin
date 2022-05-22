package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommonController {

    private final CommonServiceImpl loginService;

    @Autowired
    public CommonController(CommonServiceImpl loginService){
        this.loginService=loginService;
    }

    @GetMapping("/login")
    public ApiResponse login(@RequestHeader("x-wx-openid")String openID){
        return loginService.login(openID);
    }

    @PostMapping("/updateAvatar")
    public ApiResponse updateAvatar(@RequestHeader("x-wx-openid")String openID, @RequestBody String avatarUrl){
        return loginService.updateAvatar(openID, avatarUrl);
    }
}
