package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.LoginPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
@Mapper
public interface LoginDao {
    LoginPO login();
}
