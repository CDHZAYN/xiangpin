package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.HRRegisterDTO;

public interface HRService {
    ApiResponse HRRegister(String openID, HRRegisterDTO HRRegister);


}
