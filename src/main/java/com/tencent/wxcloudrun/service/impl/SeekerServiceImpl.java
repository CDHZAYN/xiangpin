package com.tencent.wxcloudrun.service.impl;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.po.SeekerPO;
import com.tencent.wxcloudrun.service.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SeekerServiceImpl implements SeekerService {

    private final SeekerDao seekerDao;

    @Autowired
    public SeekerServiceImpl(SeekerDao seekerDao) {
        this.seekerDao = seekerDao;
    }

    public ApiResponse login(String openID) {
        SeekerPO seekerPO = null;
        try {
            seekerPO=seekerDao.login();
        } catch (Exception e) {
            return ApiResponse.error("Error occurred when select a certain seeker.");
        }

        //TODO:need to replace
        String avatarCloudID=null;
        if(seekerPO==null)
            return ApiResponse.error("Seeker not found");
        if(avatarCloudID==null)
            return ApiResponse.error("Seeker not found.");
        return ApiResponse.ok(avatarCloudID);
    }

    public ApiResponse updateAvatar(String openID, String avatarCloudID) {
        String seekerOpenID = null;
        try {
            seekerOpenID=seekerDao.getOpenID(openID);
        } catch (Exception e) {
            return ApiResponse.error("Error occurred when select a certain seeker.");
        }
        if(seekerOpenID==null)
            return ApiResponse.error("Seeker not found.");

        //TODO:need to replace
        return ApiResponse.ok();
    }

    public ApiResponse loginTest() {
        return ApiResponse.ok(seekerDao.login());
    }

    public boolean verifyEmail(String userKey) {
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
