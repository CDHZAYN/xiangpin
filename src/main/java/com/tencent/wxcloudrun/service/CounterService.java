package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.vo.CounterVO;

import java.util.Optional;

public interface CounterService {

  Optional<CounterVO> getCounter(Integer id);

  void upsertCount(CounterVO counterVO);

  void clearCount(Integer id);
}
