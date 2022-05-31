package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.CommonDao;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.RegisterDTO;
import com.tencent.wxcloudrun.model.po.LoginPO;
import com.tencent.wxcloudrun.model.po.SeekerPO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import com.tencent.wxcloudrun.service.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SeekerServiceImpl implements SeekerService {

    private final SeekerDao seekerDao;

    @Autowired
    public SeekerServiceImpl(SeekerDao seekerDao) {
        this.seekerDao = seekerDao;
    }

    public ApiResponse seekerRegister(String openID, RegisterDTO registerDTO) {
        SeekerPO seekerPO = new SeekerPO();
        seekerPO.setOpenID(openID);
        seekerPO.setEducation(registerDTO.getAcaBg());
        seekerPO.setGender(registerDTO.getGender().equals("male"));
        seekerPO.setName(registerDTO.getName());
        seekerPO.setPhone(registerDTO.getPhoneNum());
        //TODO: need to update old

        int birthYear = Integer.parseInt(registerDTO.getYear());
        int birthMonth = Integer.parseInt(registerDTO.getMonth());
        int old = LocalDate.now().getYear() - birthYear;
        if(LocalDate.now().getMonthValue()<=birthMonth)
            old++;
        seekerPO.setOld(old);
        seekerPO.setBirth(Date.valueOf(LocalDate.of(birthYear, birthMonth, 1)));

        LoginPO loginPO = new LoginPO();
        loginPO.setOpenID(openID);
        loginPO.setUserName(registerDTO.getName());
        loginPO.setUserAvatar(registerDTO.getAvatarUrl());
        if(loginPO.getUserAvatar()==null)
            loginPO.setUserAvatar("null");

        try {
            seekerDao.setSeekerInfo(seekerPO);
            seekerDao.setSeekerLoginInfo(loginPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00003", ErrorList.errorList.get("00003"));
        }

        LoginVO loginVO = new LoginVO();
        loginVO.setUserName(loginPO.getUserName());
        loginVO.setUserAvatar(loginPO.getUserAvatar());
        return ApiResponse.ok(loginVO);
    }

}
