package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.HRPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("pro")
class HRDaoTest {
    @Autowired
    HRDao dao;

    @Test
    @Rollback
    void registerTest() {
        HRPO hrpo = new HRPO("111","母舰","19968298043","male", "120");
        if (dao.getByOpenId("111") != null) {
            dao.deleteByOpenId("111");
        }
        dao.setHRInfo(hrpo);
        assertEquals(hrpo, dao.getByOpenId("111"));
        dao.deleteByOpenId("111");
    }

    @Test
    @Rollback
    void registerLoginTest() {
        HRPO hrPO = new HRPO("111","母舰","19968298043","male", "120");
        dao.setHRInfo(hrPO);
        dao.deleteLoginByOpenId("111");
    }

    @Test
    @Rollback
    void getHRTest() {
        System.out.println(dao.getByOpenId("111"));
    }
}