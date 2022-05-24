package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.RegisterDTO;
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
    public ApiResponse register(@RequestHeader("x-wx-openid")String openID, @RequestBody RegisterDTO registerDTO){
        return seekerService.seekerRegister(openID, registerDTO);
    }
}
