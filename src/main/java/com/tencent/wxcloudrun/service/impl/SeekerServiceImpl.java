package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.SeekerBasicDTO;
import com.tencent.wxcloudrun.model.dto.SeekerIntentionDTO;
import com.tencent.wxcloudrun.model.dto.SeekerRegisterDTO;
import com.tencent.wxcloudrun.model.po.SeekerLoginPO;
import com.tencent.wxcloudrun.model.po.SeekerBasicPO;
import com.tencent.wxcloudrun.model.po.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import com.tencent.wxcloudrun.service.SeekerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ApiResponse seekerLogin(String openID) {
        String userOpenID;
        try {
            userOpenID = seekerDao.getOpenID(openID);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00001", ErrorList.errorList.get("00001"));
        }
        if (userOpenID == null)
            return ApiResponse.error("00002", ErrorList.errorList.get("00002"));

        return getSeekerProfile(openID);
    }

    public ApiResponse seekerRegister(String openID, SeekerRegisterDTO seekerRegisterDTO) {

        SeekerBasicDTO newSeekerBasic = seekerRegisterDTO.getBasic();
        List<SeekerIntentionDTO> seekerIntentionDTOList = seekerRegisterDTO.getIntention();

        //handle basic
        //TODO: need to update old
        SeekerBasicPO seekerBasicPO = new SeekerBasicPO();
        seekerBasicPO.setOpenID(openID);
        seekerBasicPO.setEducation(newSeekerBasic.getAcaBg());
        seekerBasicPO.setGender(newSeekerBasic.getGender().equals("male"));
        seekerBasicPO.setName(newSeekerBasic.getName());
        seekerBasicPO.setPhone(newSeekerBasic.getPhoneNum());

        int birthYear = Integer.parseInt(newSeekerBasic.getYear());
        int birthMonth = Integer.parseInt(newSeekerBasic.getMonth());
        int old = LocalDate.now().getYear() - birthYear;
        if (LocalDate.now().getMonthValue() <= birthMonth)
            old++;
        seekerBasicPO.setOld(old);
        seekerBasicPO.setBirth(Date.valueOf(LocalDate.of(birthYear, birthMonth, 1)));

        SeekerLoginPO seekerLoginPO = new SeekerLoginPO();
        seekerLoginPO.setOpenID(openID);
        seekerLoginPO.setUserName(newSeekerBasic.getName());
        seekerLoginPO.setUserAvatar(newSeekerBasic.getAvatarUrl());

        //execute intention
        List<SeekerIntentionPO> seekerIntentionPOList = new ArrayList<>();
        for (SeekerIntentionDTO seekerIntentionDTO : seekerIntentionDTOList) {
            SeekerIntentionPO seekerIntentionPO = new SeekerIntentionPO();
            seekerIntentionPO.setOpenID(openID);
            seekerIntentionPO.setJobType(seekerIntentionDTO.getJobType().toString());
            seekerIntentionPO.setExpIndustry(seekerIntentionDTO.getExpIndustry().toString());
            seekerIntentionPO.setJobType(seekerIntentionPO.getJobType());
            seekerIntentionPO.setSalaryType(seekerIntentionDTO.getSalaryType());

            int minSalary = seekerIntentionDTO.getExpMinSalary();
            int maxSalary = seekerIntentionDTO.getExpMaxSalary();
            seekerIntentionPO.setExpMaxSalary(Math.max(minSalary, maxSalary));
            seekerIntentionPO.setExpMinSalary(Math.min(minSalary, maxSalary));

            seekerIntentionPOList.add(seekerIntentionPO);
        }

        //execute
        //TODO: asymmetric
        try {
            seekerDao.setSeekerInfo(seekerBasicPO);
            seekerDao.setSeekerLoginInfo(seekerLoginPO);
            seekerDao.setSeekerIntentionInfo(seekerIntentionPOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00003", ErrorList.errorList.get("00003"));
        }

        LoginVO loginVO = new LoginVO();
        loginVO.setUserName(seekerLoginPO.getUserName());
        loginVO.setUserAvatar(seekerLoginPO.getUserAvatar());
        loginVO.setOpenID(openID);
        return ApiResponse.ok(loginVO);
    }

    public ApiResponse getSeekerProfile(String openID) {
        SeekerLoginPO seekerLoginPO = seekerDao.getLoginInfo(openID);
        LoginVO loginVO = new LoginVO();
        if (seekerLoginPO.getUserAvatar() == null)
            loginVO.setUserAvatar(null);
        else
            loginVO.setUserAvatar(seekerLoginPO.getUserAvatar());
        loginVO.setUserName(seekerLoginPO.getUserName());
        loginVO.setOpenID(openID);
        return ApiResponse.ok(loginVO);
    }

}
