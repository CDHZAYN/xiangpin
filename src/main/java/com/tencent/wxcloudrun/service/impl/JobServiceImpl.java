package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.JobDao;
import com.tencent.wxcloudrun.model.dto.JobDTO;
import com.tencent.wxcloudrun.model.po.JobPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl {

    private final JobDao jobDao;

    @Autowired
    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }


    public ApiResponse create(JobDTO jobDTO) {
        JobPO jobPO = new JobPO();
        BeanUtils.copyProperties(jobDTO, jobPO);
        if (jobDTO.getKeywords() != null) {
            List<String> keywords = jobDTO.getKeywords();
            StringBuffer keywordsStr = new StringBuffer();
            for (String s : keywords) {
                keywordsStr.append("%`p^");
                keywordsStr.append(s);
            }
            keywordsStr.delete(0, 4);
            jobPO.setKeywords(keywordsStr.toString());
        }
        return ApiResponse.ok();
    }

}
