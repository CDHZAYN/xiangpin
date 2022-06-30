package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.HRPO;
import com.tencent.wxcloudrun.model.po.JobPO;
import com.tencent.wxcloudrun.model.po.SearchPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobDao {

    Integer insertJob(JobPO jobPO);

    void updateJob(JobPO jobPO);

    void connectHR(Integer jobId, String openId);

    void deleteById(Integer id);

    JobPO selectJobById(Integer jobId);

    HRPO getHRByJobId(Integer jobId);

    List<JobPO> getByHRId(String openId);

    List<JobPO> getRecommend(SearchPO searchPO);

}
