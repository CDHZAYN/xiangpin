package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.controller.chatController.WebSocketServerEndpointConfig;
import com.tencent.wxcloudrun.model.po.HRLoginPO;
import com.tencent.wxcloudrun.model.po.HRPO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("pro")
class HRDaoTest {
    @Autowired
    HRDao dao;

    @Test
    void registerTest() {
        HRPO hrpo = new HRPO("111", true, "母舰", "120");
        if (dao.getByOpenId("111") != null) {
            dao.deleteByOpenId("111");
        }
        dao.setHRInfo(hrpo);
        assertEquals(hrpo, dao.getByOpenId("111"));
        dao.deleteByOpenId("111");
    }

    @Test
    void registerLoginTest() {
        HRLoginPO hrLoginPO = new HRLoginPO("母舰", "111.jpg", "111");
        dao.setHRLoginInfo(hrLoginPO);
        dao.deleteLoginByOpenId("111");
    }

    @Test
    void getHRTest() {
        System.out.println(dao.getByOpenId("111"));
    }
}