package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;
import com.tencent.wxcloudrun.service.impl.SeekerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/seeker")
public class SeekerController{

    private final SeekerServiceImpl seekerService;

    @Autowired
    public SeekerController(SeekerServiceImpl seekerServiceImpl) {
        this.seekerService = seekerServiceImpl;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestHeader("x-wx-openid")String openID, @RequestBody SeekerRegisterDTO registerDTO){
        return seekerService.seekerRegister(openID, registerDTO);
    }

    @GetMapping("/login")
    public ApiResponse login(@RequestHeader("x-wx-openid")String openID){
        return seekerService.seekerLogin(openID);
    }

    @GetMapping("/profile")
    public ApiResponse getProfile(@RequestBody String openID){
        return seekerService.getSeekerProfile(openID);
    }
}
