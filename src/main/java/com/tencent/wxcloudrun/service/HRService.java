package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.HRDao;
import com.tencent.wxcloudrun.model.dto.HRDTO;
import com.tencent.wxcloudrun.model.po.HRPO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public interface HRService {

    public ApiResponse HRRegister(String openID, HRDTO hrDTO);

    public ApiResponse HRLogin(String openId);

    public ApiResponse getHRProfile(String openID);

    public ApiResponse getFullInfo(String openId);

    public ApiResponse addRecruit(String openId, String seekerId);
}
