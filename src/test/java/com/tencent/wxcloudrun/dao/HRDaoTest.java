package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.HRPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HRDaoTest {
    @Autowired
    HRDao dao;
    @Test
    void registerTest() {
        HRPO hrpo = new HRPO("111", true, "母舰", "120");

        dao.setHRInfo(hrpo);
    }

}