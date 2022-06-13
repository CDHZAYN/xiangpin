package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.seeker.SeekerBasicPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerLoginPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SeekerDao {

    void setSeekerInfo(SeekerBasicPO seekerPO);

    void setSeekerLoginInfo(SeekerLoginPO seekerLoginPO);

    void setSeekerIntentionInfo(List<SeekerIntentionPO> seekerIntentionPOList);

    SeekerLoginPO getLoginInfo(@Param("openID") String openID);

    String getOpenID(@Param("openID") String openID);

}
