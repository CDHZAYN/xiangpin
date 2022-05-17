package com.xiangpin.dao;

import com.xiangpin.model.po.SeekerPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    List<SeekerPO> testName();

}
