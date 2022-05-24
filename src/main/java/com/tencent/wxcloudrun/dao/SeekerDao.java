package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.LoginPO;
import com.tencent.wxcloudrun.model.po.SeekerPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SeekerDao {

    void setSeekerInfo(SeekerPO seekerPO);

    void setSeekerLoginInfo(LoginPO loginPO);

}
