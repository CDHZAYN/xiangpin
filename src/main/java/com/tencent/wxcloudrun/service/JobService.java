package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.JobDTO;
import com.tencent.wxcloudrun.model.po.CompanyPO;
import com.tencent.wxcloudrun.model.po.HRPO;
import com.tencent.wxcloudrun.model.po.JobPO;
import com.tencent.wxcloudrun.model.po.SearchPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.vo.CompanyProfileVO;
import com.tencent.wxcloudrun.model.vo.JobProfileVO;
import com.tencent.wxcloudrun.model.vo.JobVO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import org.apdplat.word.analysis.CosineTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface JobService {

    public ApiResponse create(String openId, JobDTO jobDTO);

    public ApiResponse modify(JobDTO jobDTO);

    public ApiResponse delete(Integer jobId);

    public ApiResponse getFullInfo(Integer jobId);

    public ApiResponse getProfile(Integer jobId);

    public ApiResponse getRecommend(String openId, Integer posId);

    ApiResponse getResponsible(String openId);

    ApiResponse search(SearchPO searchPO);
}
