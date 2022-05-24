package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.RegisterDTO;
import com.tencent.wxcloudrun.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
public class CommonController {

    private final CommonServiceImpl commonService;

    @Autowired
    public CommonController(CommonServiceImpl commonService){
        this.commonService = commonService;
    }

    @GetMapping("/login")
    public ApiResponse login(@RequestHeader("x-wx-openid")String openID){
        return commonService.login(openID);
    }

}
