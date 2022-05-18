package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.SeekerDao;

public interface SeekerService {

    ApiResponse login(String openID);

    boolean verifyEmail(String userKey);


}
