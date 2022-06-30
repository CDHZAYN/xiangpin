package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.seeker.SeekerPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SeekerDao {

    void setSeeker(SeekerPO seekerPO);

    void setSeekerIntention(List<SeekerIntentionPO> seekerIntentionPOList);

    SeekerPO getSeeker(String openId);

    String getOpenId(String openId);

    SeekerIntentionPO getIntentionById(String openId);

    void collectByJobId(String openId, Integer jobId);

    List<Integer> getCollection(String openId);

    void deleteByIds(String openId, Integer jobId);



}
