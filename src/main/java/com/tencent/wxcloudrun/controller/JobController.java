package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.JobDTO;
import com.tencent.wxcloudrun.model.po.SearchPO;
import com.tencent.wxcloudrun.service.impl.JobServiceImpl;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobServiceImpl jobService;

    @Autowired
    public JobController(JobServiceImpl jobService){
        this.jobService = jobService;
    }

    @PostMapping("/create")
    public ApiResponse create(@RequestHeader("x-wx-openid")String openId, @RequestBody JobDTO jobDTO){
        return ApiResponse.ok(jobService.create(openId, jobDTO));
    }

    @PostMapping("/modify")
    public ApiResponse modify(@RequestHeader JobDTO jobDTO){return jobService.modify(jobDTO);}

    @DeleteMapping("/delete")
    public ApiResponse delete(@RequestHeader Integer jobId){return jobService.delete(jobId);}

    @GetMapping("/full")
    public ApiResponse getFullInfo(@RequestHeader Integer jobId){
        return jobService.getFullInfo(jobId);
    }

    @GetMapping("/profile")
    public ApiResponse getProfile(@RequestHeader Integer jobId){
        return jobService.getProfile(jobId);
    }

    @GetMapping("/getRecommend")
    public ApiResponse getProfile(@RequestHeader("x-wx-openid") String openId, @RequestHeader("cityId") Integer cityId){
        return jobService.getRecommend(openId, cityId);
    }

    @GetMapping("/getResponsible")
    public ApiResponse getResponsible(@RequestHeader("x-wx-openid") String openId){
        return jobService.getResponsible(openId);
    }

    @GetMapping("/search")
    public ApiResponse search(SearchPO searchPO){
        return jobService.search(searchPO);
    }
}
