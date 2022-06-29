package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.HRDTO;

public interface HRService {
    ApiResponse HRRegister(String openID, HRDTO HRRegister);

    ApiResponse HRLogin(String openID);
}
