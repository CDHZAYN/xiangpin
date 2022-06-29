package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.HRDTO;
import com.tencent.wxcloudrun.service.HRService;
import com.tencent.wxcloudrun.service.impl.HRServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HRController {
    private final HRService hrService;

    @Autowired
    public HRController(HRService hrService) {
        this.hrService = hrService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestHeader("x-wx-openid")String openID, @RequestBody HRDTO registerDTO){
        return hrService.HRRegister(openID, registerDTO);
    }

    @GetMapping("/login")
        public ApiResponse login(@RequestHeader("x-wx-openid")String openID) {
        return hrService.HRLogin(openID);
    }

    @GetMapping("/profile")
    public ApiResponse getProfile(@RequestHeader String openId){
        return hrService.getHRProfile(openId);
    }

    @GetMapping("/full")
    public ApiResponse getFullInfo(@RequestHeader String openId){
        return hrService.getFullInfo(openId);
    }

    @GetMapping("/recruit")
    public ApiResponse addRecruit(@RequestHeader("x-wx-openid")String openId, @RequestHeader String seekerId){
        return hrService.addRecruit(openId, seekerId);
    }
}
