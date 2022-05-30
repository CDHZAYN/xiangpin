package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.HRPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HRDao {

    void setHRInfo(HRPO hrpo);
}
