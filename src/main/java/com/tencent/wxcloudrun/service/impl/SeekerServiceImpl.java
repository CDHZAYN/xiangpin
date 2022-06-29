package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerIntentionDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.vo.seeker.SeekerLoginVO;
import com.tencent.wxcloudrun.service.SeekerService;
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

    public ApiResponse seekerLogin(String openID) {
        //TODO: need to update old
        String userOpenID;
        try {
            userOpenID = seekerDao.getOpenId(openID);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00001", ErrorList.errorList.get("00001"));
        }
        if (userOpenID == null)
            return ApiResponse.error("00002", ErrorList.errorList.get("00002"));

        return getSeekerProfile(openID);
    }

    @Transactional
    public ApiResponse seekerRegister(String openID, SeekerRegisterDTO seekerRegisterDTO) {

        SeekerDTO newSeekerBasic = seekerRegisterDTO.getSeeker();
        SeekerIntentionDTO seekerIntentionDTO = seekerRegisterDTO.getIntention();

        //handle basic
        SeekerPO seekerPO = new SeekerPO();
        seekerPO.setOpenID(openID);
        seekerPO.setEducation(newSeekerBasic.getAcaBg());
        seekerPO.setGender(newSeekerBasic.getGender().equals("male"));
        seekerPO.setName(newSeekerBasic.getName());
        seekerPO.setPhone(newSeekerBasic.getPhoneNum());
        seekerPO.setAvatar(newSeekerBasic.getAvatarUrl());

        int birthYear = Integer.parseInt(newSeekerBasic.getYear());
        int birthMonth = Integer.parseInt(newSeekerBasic.getMonth());
        int old = LocalDate.now().getYear() - birthYear;
        if (LocalDate.now().getMonthValue() <= birthMonth)
            old++;
        seekerPO.setOld(old);
        seekerPO.setBirth(Date.valueOf(LocalDate.of(birthYear, birthMonth, 1)));

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
            seekerDao.setSeeker(seekerPO);
            seekerDao.setSeekerIntention(seekerIntentionPOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00003", ErrorList.errorList.get("00003"));
        }

        SeekerLoginVO seekerLoginVO = new SeekerLoginVO();
        seekerLoginVO.setUserName(seekerPO.getName());
        seekerLoginVO.setUserAvatar(seekerPO.getAvatar());
        seekerLoginVO.setOpenID(openID);
        return ApiResponse.ok(seekerLoginVO);
    }

    public ApiResponse getSeekerProfile(String openID) {
        SeekerPO seekerPO = seekerDao.getSeeker(openID);
        SeekerLoginVO seekerLoginVO = new SeekerLoginVO();
        if (seekerPO.getAvatar() == null)
            seekerLoginVO.setUserAvatar(null);
        else
            seekerLoginVO.setUserAvatar(seekerPO.getAvatar());
        seekerLoginVO.setUserName(seekerPO.getName());
        seekerLoginVO.setOpenID(openID);
        return ApiResponse.ok(seekerLoginVO);
    }

}
