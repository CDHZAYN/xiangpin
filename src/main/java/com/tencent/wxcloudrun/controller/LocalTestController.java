package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.impl.SeekerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class LocalTestController {

    SeekerServiceImpl seekerServiceImpl;

    @Autowired
    LocalTestController(SeekerServiceImpl seekerServiceImpl){
        this.seekerServiceImpl=seekerServiceImpl;
    }

    @GetMapping("/login")
    public ApiResponse login(){
        return seekerServiceImpl.loginTest();
    }

}
