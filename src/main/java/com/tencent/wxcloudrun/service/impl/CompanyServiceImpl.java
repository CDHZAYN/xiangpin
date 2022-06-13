package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.CompanyDao;
import com.tencent.wxcloudrun.model.dto.CompanyDTO;
import com.tencent.wxcloudrun.model.po.CompanyPO;
import com.tencent.wxcloudrun.model.vo.CompanyProfileVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CompanyServiceImpl {

    private final CompanyDao companyDao;

    @Autowired
    public CompanyServiceImpl(CompanyDao companyDao){
        this.companyDao = companyDao;
    }

    public ApiResponse register(CompanyDTO companyDTO){
        CompanyPO companyPO = new CompanyPO();
        BeanUtils.copyProperties(companyDTO, companyPO);
        List<String> keywords = companyDTO.getKeywords();
        StringBuffer keywordStr = new StringBuffer();
        for(String k : keywords){
            keywordStr.append(k);
            keywordStr.append("&");
        }
        companyPO.setKeywords(keywordStr.toString());
        Integer id = companyDao.register(companyPO);
        return getProfile(id);
    }

    public ApiResponse getProfile(Integer id){
        CompanyPO companyPO = companyDao.getProfile(id);
        CompanyProfileVO companyProfileVO = new CompanyProfileVO();
        BeanUtils.copyProperties(companyPO, companyProfileVO);
        List<String> keywords = Arrays.asList(companyPO.getKeywords().split("&"));
        companyProfileVO.setKeywords(keywords);
        return ApiResponse.ok(companyProfileVO);
    }

}
