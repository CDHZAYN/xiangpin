package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.CompanyPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyDao {

    Integer register(CompanyPO companyPO);

    CompanyPO getById(Integer id);

    void modify(CompanyPO companyPO);

    void delete(Integer id);

    CompanyPO getCompanyByHRId(String openId);
}
