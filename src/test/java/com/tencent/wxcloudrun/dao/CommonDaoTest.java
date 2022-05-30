package com.tencent.wxcloudrun.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommonDaoTest {
    @Autowired
    CommonDao commonDao;

    @Test
    void getOpenIdTest() {
        //assertNull(commonDao.getOpenID("oriVB48y-JNhIqLsdf8W1adz7j0o"));
        System.out.println(commonDao.getOpenID("oriVB48y-JNhIqLsdf8W1adz7j0o"));
    }

    @Test
    void getCommonTest() {
        System.out.println(commonDao.getLoginInfo("oriVB48y-JNhIqLsdf8W1adz7j0o"));
    }
}