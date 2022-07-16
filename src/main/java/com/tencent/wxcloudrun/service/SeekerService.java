package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;

public interface SeekerService {

    ApiResponse seekerLogin(String openId);

    ApiResponse seekerRegister(String openId, SeekerRegisterDTO seekerRegisterDTO);

    ApiResponse getSeekerProfile(String openId);

    ApiResponse collectJob(String openId, Integer jobId);

    ApiResponse getCollectList(String openId);

    ApiResponse deleteCollect(String openId, Integer jobId);
}
