package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.HRDao;
import com.tencent.wxcloudrun.model.dto.HRRegisterDTO;
import com.tencent.wxcloudrun.model.po.HRLoginPO;
import com.tencent.wxcloudrun.model.po.HRPO;
import com.tencent.wxcloudrun.model.vo.HRLoginVO;
import com.tencent.wxcloudrun.service.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRServiceImpl implements HRService {

    private final HRDao hrDao;

    @Autowired
    public HRServiceImpl(HRDao hrDao) {
        this.hrDao = hrDao;
    }

    @Override
    public ApiResponse HRRegister(String openID, HRRegisterDTO hrRegister) {
        HRPO hrpo = new HRPO();
        hrpo.setGender(hrRegister.getGender().equals("male"));
        hrpo.setName(hrRegister.getName());
        hrpo.setOpenId(openID);
        hrpo.setPhoneNum(hrRegister.getPhoneNum());

        HRLoginPO loginPO = new HRLoginPO();
        loginPO.setUserAvatar(hrRegister.getAvatarUrl());
        loginPO.setUserName(hrRegister.getName());
        loginPO.setOpenID(openID);

        if (loginPO.getUserAvatar() == null) {
            loginPO.setUserAvatar("null");
        }

        try {
            hrDao.setHRInfo(hrpo);
            hrDao.setHRLoginInfo(loginPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00003", "Error occurred when register.");
        }

        HRLoginVO loginVO = new HRLoginVO();
        loginVO.setUserAvatar(loginPO.getUserAvatar());
        loginVO.setUserName(loginPO.getUserName());
        return ApiResponse.ok(loginVO);
    }

    @Override
    public ApiResponse HRLogin(String openID) {
        String userOpenId = null;

        try {
            userOpenId = hrDao.getByOpenId(openID).getOpenId();
        } catch (Exception e) {
            return ApiResponse.error("00001", "Error occurred when select a certain user.");
        }

        if (userOpenId == null) {
            return ApiResponse.error("00002", "User not found.");
        }

        HRLoginPO hrLoginPO = hrDao.getLoginByOpenId(openID);
        HRLoginVO hrLoginVO = new HRLoginVO();

        hrLoginVO.setUserName(hrLoginPO.getUserName());
        if (hrLoginPO.getUserAvatar() == null) {
            hrLoginVO.setUserAvatar(null);
        } else {
            hrLoginVO.setUserAvatar(hrLoginPO.getUserAvatar());
        }
        return ApiResponse.ok(hrLoginVO);
    }
}
