package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.HRPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface HRDao {

    void setHRInfo(HRPO hrpo);

    HRPO getByOpenId(String openID);

    String getOpenId(String openID);

    void deleteByOpenId(String openID);

    void connectCompany(String openId, String companyId);
}
