package com.tencent.wxcloudrun.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("pro")
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    void test() {
        messageService.setHasRead("123", "125", "1655021984670");
    }
}