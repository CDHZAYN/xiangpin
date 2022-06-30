package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;
import com.tencent.wxcloudrun.service.SeekerService;
import com.tencent.wxcloudrun.service.impl.SeekerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/seeker")
public class SeekerController{

    private final SeekerService seekerService;

    @Autowired
    public SeekerController(SeekerService seekerService) {
        this.seekerService = seekerService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestHeader("x-wx-openid")String openId, @RequestBody SeekerRegisterDTO registerDTO){
        return seekerService.seekerRegister(openId, registerDTO);
    }

    @GetMapping("/login")
    public ApiResponse login(@RequestHeader("x-wx-openid")String openId){
        return seekerService.seekerLogin(openId);
    }

    @GetMapping("/profile")
    public ApiResponse getProfile(@RequestHeader String openId){
        return seekerService.getSeekerProfile(openId);
    }
}
