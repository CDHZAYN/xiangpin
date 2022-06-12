package com.tencent.wxcloudrun.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.xml.crypto.Data;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("pro")
class MessageDaoTest {
    @Autowired
    MessageDao dao;

    @Test
    void test() {
        long time = 1655016890;
        Date temp = new Date(1655016866 * 1000L);
        System.out.println(temp);
        System.out.println(dao.selectMessageByAccepterIDAndTime("124", temp));
    }
}