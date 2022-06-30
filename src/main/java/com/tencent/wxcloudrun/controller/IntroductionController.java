package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.IntroductionDTO;
import com.tencent.wxcloudrun.service.IntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 11731
 */

@RestController
@RequestMapping("/introduction")
public class IntroductionController {

    private final IntroductionService introductionService;

    @Autowired
    IntroductionController(IntroductionService introductionService) {
        this.introductionService = introductionService;
    }

    @PostMapping("/create")
    public ApiResponse createIntroduction(@RequestHeader("x-wx-openid")String openId, @RequestBody IntroductionDTO introductionDTO) {
        return ApiResponse.ok(introductionService.createIntroduction(introductionDTO, openId));
    }

    @GetMapping("/query")
    public ApiResponse queryIntroduction(@RequestHeader("x-wx-openid")String openId) {
        return ApiResponse.ok(introductionService.queryIntroductionByOpenId(openId));
    }

    @DeleteMapping("/delete")
    public ApiResponse deleteIntroduction(@RequestHeader("x-wx-openid")String openId) {
        return ApiResponse.ok(introductionService.deleteIntroductionByOpenId(openId));
    }

    @PostMapping("/update")
    public ApiResponse updateIntroduction(@RequestHeader("x-wx-openid")String openId, @RequestBody IntroductionDTO introductionDTO) {
        return ApiResponse.ok(introductionService.updateIntroduction(introductionDTO, openId));
    }
}
