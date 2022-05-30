package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.HRRegisterDTO;
import com.tencent.wxcloudrun.model.dto.RegisterDTO;
import com.tencent.wxcloudrun.service.impl.HRServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HRController {
    private final HRServiceImpl hrService;

    @Autowired
    public HRController(HRServiceImpl hrService) {
        this.hrService = hrService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestHeader("x-wx-openid")String openID, @RequestBody HRRegisterDTO registerDTO){
        return hrService.HRRegister(openID, registerDTO);
    }

}