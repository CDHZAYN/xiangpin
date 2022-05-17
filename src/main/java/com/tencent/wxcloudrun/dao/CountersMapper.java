package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.vo.CounterVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CountersMapper {

  CounterVO getCounter(@Param("id") Integer id);

  void upsertCount(CounterVO counterVO);

  void clearCount(@Param("id") Integer id);
}
