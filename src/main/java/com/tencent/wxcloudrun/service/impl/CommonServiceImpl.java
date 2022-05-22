package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.CommonDao;
import com.tencent.wxcloudrun.model.po.LoginPO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import com.tencent.wxcloudrun.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

    private final CommonDao commonDao;

    @Autowired
    public CommonServiceImpl(CommonDao commonDao){
        this.commonDao = commonDao;
    }

    public ApiResponse login(String openID){
        LoginPO loginPO = null;
        try {
            loginPO= commonDao.login(openID);
        } catch (Exception e) {
            return ApiResponse.error("00001","Error occurred when select a certain seeker.");
        }
        if(loginPO==null)
            return ApiResponse.error("00002","Seeker not found");

        LoginVO loginVO = new LoginVO();
        loginVO.setUserName(loginPO.getUserName());
        if(loginPO.getUserAvatar().equals("NULL"))
            loginVO.setUserAvatar(null);
        else
            loginVO.setUserAvatar(loginPO.getUserAvatar());
        return ApiResponse.ok(loginVO);
    }

    public ApiResponse updateAvatar(String openID, String avatarCloudID) {
        String seekerOpenID = null;
        try {
            seekerOpenID=commonDao.getOpenID(openID);
        } catch (Exception e) {
            return ApiResponse.error("00001","Error occurred when select a certain seeker.");
        }
        if(seekerOpenID==null)
            return ApiResponse.error("00002","Seeker not found.");

        //TODO:need to replace
        return ApiResponse.ok();
    }

}
