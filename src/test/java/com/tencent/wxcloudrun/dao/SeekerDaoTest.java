package com.tencent.wxcloudrun.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeekerDaoTest {
    @Autowired
    SeekerDao seekerDao;

    @Test
    void getOpenIdTest() {
        assertNull(seekerDao.login());
        System.out.println(seekerDao.login());
    }
}