package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.CompanyDTO;
import com.tencent.wxcloudrun.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyServiceImpl companyService;

    @Autowired
    public CompanyController(CompanyServiceImpl companyService){
        this.companyService = companyService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody CompanyDTO companyDTO){
        return companyService.register(companyDTO);
    }

}
