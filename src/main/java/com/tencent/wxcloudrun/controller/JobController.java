package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.JobDTO;
import com.tencent.wxcloudrun.service.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobServiceImpl jobService;

    @Autowired
    public JobController(JobServiceImpl jobService){
        this.jobService = jobService;
    }

    @PostMapping("/create")
    public ApiResponse create(@RequestBody JobDTO jobDTO){
        return ApiResponse.ok(jobService.create(jobDTO));
    }
}
