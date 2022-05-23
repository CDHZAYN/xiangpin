package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.impl.SeekerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/seeker")
public class SeekerController{

    private final SeekerServiceImpl seekerServiceImpl;

    @Autowired
    public SeekerController(SeekerServiceImpl seekerServiceImpl) {
        this.seekerServiceImpl = seekerServiceImpl;
    }

}
