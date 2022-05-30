package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.IntentionPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface IntentionDao {
    void createIntention(IntentionPO intention);


}
