package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerIntentionDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import com.tencent.wxcloudrun.service.SeekerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeekerServiceImpl implements SeekerService {

    private final SeekerDao seekerDao;

    @Autowired
    public SeekerServiceImpl(SeekerDao seekerDao) {
        this.seekerDao = seekerDao;
    }

    public ApiResponse seekerLogin(String openId) {
        String userOpenId = null;
        try {
            userOpenId = seekerDao.getOpenId(openId);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00001", ErrorList.errorList.get("00001"));
        }
        if (userOpenId == null)
            return ApiResponse.error("00002", ErrorList.errorList.get("00002"));

        return getSeekerProfile(openId);
    }

    @Transactional
    public ApiResponse seekerRegister(String openId, SeekerRegisterDTO seekerRegisterDTO) {

        SeekerDTO seekerDTO = seekerRegisterDTO.getSeeker();
        SeekerIntentionDTO seekerIntentionDTO = seekerRegisterDTO.getIntention();

        SeekerPO seekerPO = new SeekerPO();
        BeanUtils.copyProperties(seekerDTO, seekerPO);
        seekerPO.setOpenId(openId);

        List<SeekerIntentionPO> seekerIntentionPOList = new ArrayList<>();
        SeekerIntentionPO seekerIntentionPO = new SeekerIntentionPO();
        BeanUtils.copyProperties(seekerIntentionDTO, seekerIntentionPO);
        seekerPO.setOpenId(openId);
        seekerIntentionPOList.add(seekerIntentionPO);

        try {
            seekerDao.setSeeker(seekerPO);
            seekerDao.setSeekerIntention(seekerIntentionPOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00003", ErrorList.errorList.get("00003"));
        }
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(seekerPO, loginVO);
        return ApiResponse.ok(loginVO);
    }

    public ApiResponse getSeekerProfile(String openId) {
        SeekerPO seekerPO = seekerDao.getSeeker(openId);
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(seekerPO, loginVO);
        return ApiResponse.ok(loginVO);
    }

}
