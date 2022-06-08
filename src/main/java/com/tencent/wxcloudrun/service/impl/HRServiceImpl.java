package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.HRDao;
import com.tencent.wxcloudrun.model.dto.HRRegisterDTO;
import com.tencent.wxcloudrun.model.po.HRLoginPO;
import com.tencent.wxcloudrun.model.po.HRPO;
import com.tencent.wxcloudrun.model.po.SeekerLoginPO;
import com.tencent.wxcloudrun.model.vo.HRLoginVO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import com.tencent.wxcloudrun.service.HRService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRServiceImpl implements HRService {

    private final HRDao hrDao;

    @Autowired
    public HRServiceImpl(HRDao hrDao) {
        this.hrDao = hrDao;
    }

    /**
     * 注册功能
     * @param openID
     * @param hrRegister
     * @return  返回ApiResponse
     */
    @Override
    public ApiResponse HRRegister(String openID, HRRegisterDTO hrRegister) {
        if (hrDao.getByOpenId(openID) != null) {
            return ApiResponse.error("00004", ErrorList.errorList.get("00004"));
        }


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
            return ApiResponse.error("00003", ErrorList.errorList.get("00003"));
        }

        return getHRProfile(openID);
    }

    @Override
    public ApiResponse HRLogin(String openID) {
        String userOpenId;

        try {
            userOpenId = hrDao.getOpenId(openID);

        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00001", ErrorList.errorList.get("00001"));
        }

        if (userOpenId == null) {
            return ApiResponse.error("00002", ErrorList.errorList.get("00002"));
        }

        HRLoginPO hrLoginPO = hrDao.getLoginByOpenId(openID);
        HRLoginVO hrLoginVO = new HRLoginVO();

        hrLoginVO.setUserName(hrLoginPO.getUserName());
        hrLoginVO.setOpenID(openID);
        if (hrLoginPO.getUserAvatar() == null) {
            hrLoginVO.setUserAvatar(null);
        } else {
            hrLoginVO.setUserAvatar(hrLoginPO.getUserAvatar());
        }
        return ApiResponse.ok(hrLoginVO);
    }

    public ApiResponse getHRProfile(String openID) {
        HRLoginPO HRLoginPO = hrDao.getLoginByOpenId(openID);
        LoginVO loginVO = new LoginVO();
        if (HRLoginPO.getUserAvatar() == null)
            loginVO.setUserAvatar(null);
        else
            loginVO.setUserAvatar(HRLoginPO.getUserAvatar());
        loginVO.setUserName(HRLoginPO.getUserName());
        loginVO.setOpenID(openID);
        return ApiResponse.ok(loginVO);
    }
}
