package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.ErrorList;
import com.tencent.wxcloudrun.dao.HRDao;
import com.tencent.wxcloudrun.model.dto.HRDTO;
import com.tencent.wxcloudrun.model.po.HRPO;
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
     *
     * @param openID
     * @param hrDTO
     * @return 返回ApiResponse
     */
    @Override
    public ApiResponse HRRegister(String openID, HRDTO hrDTO) {
        if (hrDao.getByOpenId(openID) != null) {
            return ApiResponse.error("00004", ErrorList.errorList.get("00004"));
        }
        HRPO hrPO = new HRPO();
        BeanUtils.copyProperties(hrDTO, hrPO);
        hrPO.setOpenId(openID);
        try {
            hrDao.setHRInfo(hrPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00003", ErrorList.errorList.get("00003"));
        }
        return getHRProfile(openID);
    }

    @Override
    public ApiResponse HRLogin(String openId) {
        //examine whether hr has existed
        try {
            String userOpenId;
            userOpenId = hrDao.getOpenId(openId);
            if (userOpenId == null) {
                return ApiResponse.error("00002", ErrorList.errorList.get("00002"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("00001", ErrorList.errorList.get("00001"));
        }
        HRPO hrPO = hrDao.getByOpenId(openId);
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(hrPO, loginVO);
        return ApiResponse.ok(loginVO);
    }

    public ApiResponse getHRProfile(String openID) {
        HRPO hrPO = hrDao.getByOpenId(openID);
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(hrPO, loginVO);
        return ApiResponse.ok(loginVO);
    }

    public ApiResponse getFullInfo(String openId) {
        //TODO
        return ApiResponse.ok();
    }

    public ApiResponse addRecruit(String openId, String seekerId) {
        //TODO
        return ApiResponse.ok();
    }

    public ApiResponse joinCompany(String openId, String companyId) {
        hrDao.connectCompany(openId, companyId);
        return ApiResponse.ok();
    }
}
