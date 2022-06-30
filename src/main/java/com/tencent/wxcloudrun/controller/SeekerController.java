package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;
import com.tencent.wxcloudrun.service.SeekerService;
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

    @GetMapping("/collect")
    public ApiResponse collectJob(@RequestHeader("x-wx-openid")String openId, @RequestHeader Integer jobId){
        return seekerService.collectJob(openId, jobId);
    }

    @GetMapping("/collectList")
    public ApiResponse getCollectList(@RequestHeader("x-wx-openid")String openId){
        return seekerService.getCollectList(openId);
    }

    @DeleteMapping("/collectDelete")
    public ApiResponse deleteCollect(@RequestHeader("x-wx-openid")String openId, @RequestHeader Integer jobId){
        return seekerService.deleteCollect(openId, jobId);
    }
}
