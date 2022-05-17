package com.tencent.wxcloudrun.service;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public ApiResponse login(String openID, String appID) {
        if (!appID.equals("wx4ca42746bf80b0b0"))
            return ApiResponse.error("YOU ARE CONNECTED TO A WRONG BACKEND!");
        return ApiResponse.ok("received openID: " + openID);
    }

    private boolean verifyEmail(String userKey) {
        if (userKey == null)
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(userKey);
        return m.matches();
    }
}
