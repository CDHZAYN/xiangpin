package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.CompanyDTO;

public interface CompanyService {

    ApiResponse register(CompanyDTO companyDTO);

    ApiResponse getProfile(String id);

    ApiResponse getFullInfo(String id);

    ApiResponse modify(CompanyDTO companyDTO);

    ApiResponse delete(String id);
}
