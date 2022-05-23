package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.LoginPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommonDao {
    LoginPO login(@Param("openID") String openID);

    String getOpenID(@Param("openID") String openID);
}
