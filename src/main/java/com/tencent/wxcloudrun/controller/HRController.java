package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.HRRegisterDTO;
import com.tencent.wxcloudrun.model.dto.HRRegisterDTO;
import com.tencent.wxcloudrun.model.vo.HRLoginVO;
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

    @GetMapping("/login")
        public ApiResponse login(@RequestHeader("x-wx-openid")String openID) {
        return hrService.HRLogin(openID);
    }

    @GetMapping("/profile")
    public ApiResponse getProfile(@RequestHeader("x-wx-openid")String openID){
        return hrService.getHRProfile(openID);
    }

    @GetMapping("/recruit")
    public ApiResponse addRecruit(@RequestHeader("x-wx-openid")String openID){
        return hrService.addRecruit
    }
}
