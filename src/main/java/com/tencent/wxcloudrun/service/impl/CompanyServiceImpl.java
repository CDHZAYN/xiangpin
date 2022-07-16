package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.CompanyDao;
import com.tencent.wxcloudrun.dao.HRDao;
import com.tencent.wxcloudrun.model.dto.CompanyDTO;
import com.tencent.wxcloudrun.model.po.CompanyPO;
import com.tencent.wxcloudrun.model.vo.CompanyProfileVO;
import com.tencent.wxcloudrun.model.vo.CompanyVO;
import com.tencent.wxcloudrun.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyDao companyDao;

    private final HRDao hrDao;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao, HRDao hrDao) {
        this.companyDao = companyDao;
        this.hrDao = hrDao;
    }

    @Transactional
    public ApiResponse register(String openId, CompanyDTO companyDTO) {
        CompanyPO companyPO = new CompanyPO();
        BeanUtils.copyProperties(companyDTO, companyPO);
        companyPO.setId(generateId(companyPO.getShortName()));
        companyDao.register(companyPO);
        hrDao.connectCompany(openId, companyPO.getId());
        return getProfile(companyPO.getId());
    }

    public ApiResponse getProfile(String id) {
        CompanyPO companyPO = companyDao.getById(id);
        CompanyProfileVO companyProfileVO = new CompanyProfileVO();
        BeanUtils.copyProperties(companyPO, companyProfileVO);
        return ApiResponse.ok(companyProfileVO);
    }

    public ApiResponse getFullInfo(String id) {
        CompanyPO companyPO = companyDao.getById(id);
        CompanyVO companyVO = new CompanyVO();
        BeanUtils.copyProperties(companyPO, companyVO);
        return ApiResponse.ok(companyVO);
    }

    public ApiResponse modify(CompanyDTO companyDTO) {
        CompanyPO companyPO = new CompanyPO();
        BeanUtils.copyProperties(companyDTO, companyPO);
        companyDao.modify(companyPO);
        return ApiResponse.ok();
    }

    public ApiResponse delete(String id){
        companyDao.delete(id);
        return ApiResponse.ok();
    }

    private String generateId(String name){
        String md5 = null;
        md5 = DigestUtils.md5DigestAsHex((String.valueOf(System.currentTimeMillis())+name).getBytes());
        return md5;
    }

}
