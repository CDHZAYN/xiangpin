package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.CommonDao;
import com.tencent.wxcloudrun.dao.HRDao;
import com.tencent.wxcloudrun.model.dto.RegisterDTO;
import com.tencent.wxcloudrun.model.po.LoginPO;
import com.tencent.wxcloudrun.model.po.SeekerPO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import com.tencent.wxcloudrun.service.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class CommonServiceImpl implements CommonService {

    private final CommonDao commonDao;

    @Autowired
    public CommonServiceImpl(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    public ApiResponse login(String openID) {
        //TODO: add business login verify
        String userOpenID = null;
        try {
            userOpenID = commonDao.getOpenID(openID);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00001", ErrorList.errorList.get("00001"));
        }
        if (userOpenID == null)
            return ApiResponse.error("00002", ErrorList.errorList.get("00002"));

        LoginPO loginPO = commonDao.getLoginInfo(openID);
        LoginVO loginVO = new LoginVO();
        if (loginPO.getUserAvatar() == null)
            loginVO.setUserAvatar(null);
        else
            loginVO.setUserAvatar(loginPO.getUserAvatar());
        loginVO.setUserName(loginPO.getUserName());
        return ApiResponse.ok(loginVO);
    }
}
