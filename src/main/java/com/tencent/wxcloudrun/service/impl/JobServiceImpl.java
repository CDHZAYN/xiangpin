package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.CompanyDao;
import com.tencent.wxcloudrun.dao.HRDao;
import com.tencent.wxcloudrun.dao.JobDao;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.JobDTO;
import com.tencent.wxcloudrun.model.po.CompanyPO;
import com.tencent.wxcloudrun.model.po.HRLoginPO;
import com.tencent.wxcloudrun.model.po.JobPO;
import com.tencent.wxcloudrun.model.po.SearchPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.vo.CompanyProfileVO;
import com.tencent.wxcloudrun.model.vo.HRLoginVO;
import com.tencent.wxcloudrun.model.vo.JobProfileVO;
import com.tencent.wxcloudrun.model.vo.JobVO;
import org.apdplat.word.analysis.CosineTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class JobServiceImpl {

    private final JobDao jobDao;

    private final CompanyDao companyDao;

    private final HRDao hrDao;

    private final SeekerDao seekerDao;

    @Autowired
    public JobServiceImpl(JobDao jobDao, CompanyDao companyDao, HRDao hrDao, SeekerDao seekerDao) {
        this.jobDao = jobDao;
        this.companyDao = companyDao;
        this.hrDao = hrDao;
        this.seekerDao = seekerDao;
    }

    @Transactional
    public ApiResponse create(String openId, JobDTO jobDTO) {
        JobPO jobPO = new JobPO();
        BeanUtils.copyProperties(jobDTO, jobPO);
        jobDao.insertJob(jobPO);

        Integer jobId = jobPO.getId();
        jobDao.connectHR(jobId, openId);
        return ApiResponse.ok();
    }

    public ApiResponse modify(JobDTO jobDTO) {
        JobPO jobPO = new JobPO();
        BeanUtils.copyProperties(jobDTO, jobPO);
        jobDao.updateJob(jobPO);
        return ApiResponse.ok();
    }

    public ApiResponse delete(Integer jobId) {
        jobDao.deleteById(jobId);
        return ApiResponse.ok();
    }

    public ApiResponse getFullInfo(Integer jobId) {
        JobPO jobPO = jobDao.selectJobById(jobId);
        JobVO jobVO = new JobVO();
        BeanUtils.copyProperties(jobPO, jobVO);
        HRLoginPO hrLoginPO = jobDao.getHRByJobId(jobId);
        HRLoginVO hrLoginVO = new HRLoginVO();
        BeanUtils.copyProperties(hrLoginPO, hrLoginVO);
        CompanyPO companyPO = companyDao.getCompanyByHRId(hrLoginVO.getOpenID());
        CompanyProfileVO companyProfileVO = new CompanyProfileVO();
        BeanUtils.copyProperties(companyPO, companyProfileVO);
        companyProfileVO.setKeywords(Arrays.asList(companyPO.getKeywords().split("`!W~")));
        jobVO.setCompany(companyProfileVO);
        jobVO.setHrLoginVO(hrLoginVO);
        return ApiResponse.ok(jobVO);
    }

    public ApiResponse getProfile(Integer jobId) {
        JobPO jobPO = jobDao.selectJobById(jobId);
        JobProfileVO jobProfileVO = new JobProfileVO();
        BeanUtils.copyProperties(jobPO, jobProfileVO);
        HRLoginPO hrLoginPO = jobDao.getHRByJobId(jobId);
        HRLoginVO hrLoginVO = new HRLoginVO();
        BeanUtils.copyProperties(hrLoginPO, hrLoginVO);
        CompanyPO companyPO = companyDao.getCompanyByHRId(hrLoginVO.getOpenID());
        CompanyProfileVO companyProfileVO = new CompanyProfileVO();
        BeanUtils.copyProperties(companyPO, companyProfileVO);
        companyProfileVO.setKeywords(Arrays.asList(companyPO.getKeywords().split("`!W~")));
        jobProfileVO.setCompany(companyProfileVO);
        jobProfileVO.setHRName(hrLoginVO.getUserName());
        return ApiResponse.ok(jobProfileVO);
    }

    public ApiResponse getRecommend(String openId, Integer city) {
        SeekerIntentionPO seekerIntentionPO = seekerDao.getIntentionById(openId);
        SearchPO searchPO = new SearchPO();
        BeanUtils.copyProperties(seekerIntentionPO, searchPO);
        searchPO.setCity(city);
        List<JobProfileVO> jobProfileVOList = getRecommend(searchPO);
        return ApiResponse.ok(jobProfileVOList);
    }

    public ApiResponse getResponsible(String openId) {
        List<JobPO> jobPOList = jobDao.getByHRId(openId);
        jobPOList.sort(new Comparator<JobPO>() {
            @Override
            public int compare(JobPO o1, JobPO o2) {
                return o1.getJobName().compareTo(o2.getJobName());
            }
        });
        List<JobProfileVO> jobProfileVOList = POToProfileVO(jobPOList);
        return ApiResponse.ok(jobProfileVOList);
    }

    public ApiResponse search(SearchPO searchPO) {
        List<JobProfileVO> jobProfileVOList = getRecommend(searchPO);
        jobProfileVOList.sort(new Comparator<JobProfileVO>() {
            @Override
            public int compare(JobProfileVO o1, JobProfileVO o2) {
                //TODO
                TextSimilarity textSimilarity = new CosineTextSimilarity();
                double score1 = textSimilarity.similarScore(o1.getTags(), searchPO.getKeywords());
                double score2 = textSimilarity.similarScore(o2.getTags(), searchPO.getKeywords());
                return (int) (score2 * 10 - score1 * 10);
            }
        });
        return ApiResponse.ok(jobProfileVOList);
    }

    private List<JobProfileVO> getRecommend(SearchPO searchPO) {
        List<JobPO> jobPOList = jobDao.getRecommend(searchPO);
        jobPOList.sort(new Comparator<JobPO>() {
            @Override
            public int compare(JobPO o1, JobPO o2) {
                return o2.getSalaryRoof() - o1.getSalaryRoof();
            }
        });
        List<JobProfileVO> jobProfileVOList = POToProfileVO(jobPOList);
        return jobProfileVOList;
    }

    private List<JobProfileVO> POToProfileVO(List<JobPO> jobPOList) {
        List<JobProfileVO> jobProfileVOList = new ArrayList<>();
        for (JobPO jobPO : jobPOList) {
            JobProfileVO jobProfileVO = new JobProfileVO();
            BeanUtils.copyProperties(jobPO, jobProfileVO);
            HRLoginPO hrLoginPO = jobDao.getHRByJobId(jobPO.getId());
            HRLoginVO hrLoginVO = new HRLoginVO();
            BeanUtils.copyProperties(hrLoginPO, hrLoginVO);
            CompanyPO companyPO = companyDao.getCompanyByHRId(hrLoginVO.getOpenID());
            CompanyProfileVO companyProfileVO = new CompanyProfileVO();
            BeanUtils.copyProperties(companyPO, companyProfileVO);
            companyProfileVO.setKeywords(Arrays.asList(companyPO.getKeywords().split("`!W~")));
            jobProfileVO.setCompany(companyProfileVO);
            jobProfileVO.setHRName(hrLoginVO.getUserName());
            jobProfileVOList.add(jobProfileVO);
        }
        return jobProfileVOList;
    }

}
