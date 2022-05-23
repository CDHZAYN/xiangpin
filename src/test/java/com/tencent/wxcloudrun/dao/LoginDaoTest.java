package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.LoginPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginDaoTest {
    @Autowired
    LoginDao loginDao;

    @Test
    void login() {
        System.out.println(loginDao.login("1"));
    }

    @Test
    void test1() {
        System.out.println(loginDao.test());
    }
}