package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.MessagePO;
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

//    @Test
//    void test() {
//        long time = 1655016890;
//        Date temp = new Date(1655016866 * 1000L);
//        System.out.println(temp);
//        System.out.println(dao.selectMessageByAccepterIDAndTime("124", temp));
//    }
//
//    @Test
//    void test2() {
//        String time = "1655021831895";
//        Date temp = new Date(Long.parseLong(time));
//        System.out.println(temp);
//        dao.updateMessageValue("123", "125", temp);
//    }
//
//    @Test
//    void test3() {
//        String time = "1655042677000";
//        Date temp = new Date(Long.parseLong(time));
//        System.out.println(temp);
//        MessagePO messagePO = new MessagePO(0, 0, 0, "123", "125", "离谱谱", temp);
//        dao.addMessage(messagePO);
//    }
}