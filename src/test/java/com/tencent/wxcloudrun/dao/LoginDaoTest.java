package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.LoginPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginDaoTest {
    @Autowired
    CommonDao commonDao;

    @Test
    void getOpenIdTest() {
        assertNull(commonDao.login("4"));
        System.out.println(commonDao.login("4"));
    }
}