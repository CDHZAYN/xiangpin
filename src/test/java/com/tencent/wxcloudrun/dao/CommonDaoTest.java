package com.tencent.wxcloudrun.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommonDaoTest {
    @Autowired
    CommonDao commonDao;

    @Test
    void getOpenIdTest() {
        assertNull(commonDao.login("oriVB48y-JNhIqLsdf8W1adz7j0o"));
        System.out.println(commonDao.login("oriVB48y-JNhIqLsdf8W1adz7j0o"));
    }
}