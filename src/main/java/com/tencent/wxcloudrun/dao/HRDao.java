package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.CompanyPO;
import com.tencent.wxcloudrun.model.po.HRLoginPO;
import com.tencent.wxcloudrun.model.po.HRPO;
import com.tencent.wxcloudrun.model.vo.CompanyProfileVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface HRDao {

    void setHRInfo(HRPO hrpo);

    void setHRLoginInfo(HRLoginPO hrLoginInfo);

    HRPO getByOpenId(String openID);

    HRLoginPO getLoginByOpenId(String openID);

    String getOpenId(String openID);

    void deleteByOpenId(String openID);

    void deleteLoginByOpenId(String openID);
}
