package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerBasicDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerIntentionDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerLoginPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerBasicPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.vo.SeekerLoginVO;
import com.tencent.wxcloudrun.service.SeekerService;
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
        //TODO: need to update old
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
        SeekerIntentionDTO seekerIntentionDTO = seekerRegisterDTO.getIntention();

        //handle basic
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

        SeekerLoginVO seekerLoginVO = new SeekerLoginVO();
        seekerLoginVO.setUserName(seekerLoginPO.getUserName());
        seekerLoginVO.setUserAvatar(seekerLoginPO.getUserAvatar());
        seekerLoginVO.setOpenID(openID);
        return ApiResponse.ok(seekerLoginVO);
    }

    public ApiResponse getSeekerProfile(String openID) {
        SeekerLoginPO seekerLoginPO = seekerDao.getLoginInfo(openID);
        SeekerLoginVO seekerLoginVO = new SeekerLoginVO();
        if (seekerLoginPO.getUserAvatar() == null)
            seekerLoginVO.setUserAvatar(null);
        else
            seekerLoginVO.setUserAvatar(seekerLoginPO.getUserAvatar());
        seekerLoginVO.setUserName(seekerLoginPO.getUserName());
        seekerLoginVO.setOpenID(openID);
        return ApiResponse.ok(seekerLoginVO);
    }

}
