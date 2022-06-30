package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerIntentionDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerPO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface SeekerService {

    public ApiResponse seekerLogin(String openId);

    @Transactional
    public ApiResponse seekerRegister(String openId, SeekerRegisterDTO seekerRegisterDTO);

    public ApiResponse getSeekerProfile(String openId);
}
