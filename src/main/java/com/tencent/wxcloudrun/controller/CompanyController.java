package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.CompanyDTO;
import com.tencent.wxcloudrun.service.impl.CompanyServiceImpl;
import lombok.Data;
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

    @GetMapping("/profile")
    public ApiResponse getProfile(@RequestHeader Integer id){
        return companyService.getProfile(id);
    }

    @GetMapping("/full")
    public ApiResponse getFullInfo(@RequestHeader Integer id){
        return companyService.getFullInfo(id);
    }

    @PostMapping("/modify")
    public ApiResponse modify(@RequestBody CompanyDTO companyDTO){
        return companyService.modify(companyDTO);
    }

    @DeleteMapping("/delete")
    public ApiResponse delete(@RequestHeader Integer id){
        return companyService.delete(id);
    }
}

