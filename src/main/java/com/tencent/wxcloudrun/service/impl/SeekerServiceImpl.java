package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.JobDao;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerIntentionDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerRegisterDTO;
import com.tencent.wxcloudrun.model.po.JobPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.vo.JobProfileVO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import com.tencent.wxcloudrun.service.JobService;
import com.tencent.wxcloudrun.service.SeekerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeekerServiceImpl implements SeekerService {

    private final SeekerDao seekerDao;

    private final JobDao jobDao;

    private final JobService jobService;

    @Autowired
    public SeekerServiceImpl(SeekerDao seekerDao, JobDao jobDao, JobService jobService) {
        this.seekerDao = seekerDao;
        this.jobDao = jobDao;
        this.jobService = jobService;
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
        if (userOpenID == null) {
            return ApiResponse.error("00002", ErrorList.errorList.get("00002"));
        }

        return getSeekerProfile(openID);
    }

    @Transactional
    public ApiResponse seekerRegister(String openId, SeekerRegisterDTO seekerRegisterDTO) {

        SeekerDTO seekerDTO = seekerRegisterDTO.getSeeker();
        SeekerIntentionDTO seekerIntentionDTO = seekerRegisterDTO.getIntention();

        //handle basic
        SeekerPO seekerPO = new SeekerPO();
        seekerPO.setOpenId(openId);
        BeanUtils.copyProperties(seekerDTO, seekerPO);

        //execute intention
        List<SeekerIntentionPO> seekerIntentionPOList = new ArrayList<>();
        SeekerIntentionPO seekerIntentionPO = new SeekerIntentionPO();
        BeanUtils.copyProperties(seekerIntentionDTO, seekerIntentionPO);

        seekerIntentionPOList.add(seekerIntentionPO);


        //execute
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

    public ApiResponse getSeekerProfile(String openID) {
        SeekerPO seekerPO = seekerDao.getSeeker(openID);
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(seekerPO, loginVO);
        return ApiResponse.ok(loginVO);
    }

    public ApiResponse collectJob(String openId, Integer jobId){
        seekerDao.collectByJobId(openId, jobId);
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse getCollectList(String openId) {
        List<Integer> JobIdList = seekerDao.getCollection(openId);
        List<JobPO> jobPOList= jobDao.getJobListByIds(JobIdList);
        List<JobProfileVO> jobProfileVOList = jobService.POToProfileVO(jobPOList);
        return ApiResponse.ok(jobProfileVOList);
    }

    @Override
    public ApiResponse deleteCollect(String openId, Integer jobId) {
        seekerDao.deleteByIds(openId, jobId);
        return ApiResponse.ok();
    }

}
