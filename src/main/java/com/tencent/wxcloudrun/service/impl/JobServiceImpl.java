package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dao.CompanyDao;
import com.tencent.wxcloudrun.dao.HRDao;
import com.tencent.wxcloudrun.dao.JobDao;
import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.model.dto.JobDTO;
import com.tencent.wxcloudrun.model.po.CompanyPO;
import com.tencent.wxcloudrun.model.po.HRPO;
import com.tencent.wxcloudrun.model.po.JobPO;
import com.tencent.wxcloudrun.model.po.SearchPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.vo.CompanyProfileVO;
import com.tencent.wxcloudrun.model.vo.LoginVO;
import com.tencent.wxcloudrun.model.vo.JobProfileVO;
import com.tencent.wxcloudrun.model.vo.JobVO;
import com.tencent.wxcloudrun.service.JobService;
import org.apdplat.word.analysis.CosineTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

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
        HRPO hrLoginPO = jobDao.getHRByJobId(jobId);
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(hrLoginPO, loginVO);
        CompanyPO companyPO = companyDao.getCompanyByHRId(loginVO.getOpenId());
        CompanyProfileVO companyProfileVO = new CompanyProfileVO();
        BeanUtils.copyProperties(companyPO, companyProfileVO);
        jobVO.setCompany(companyProfileVO);
        jobVO.setHrVO(loginVO);
        return ApiResponse.ok(jobVO);
    }

    public ApiResponse getProfile(Integer jobId) {
        JobPO jobPO = jobDao.selectJobById(jobId);
        List<JobPO> jobPOList = new ArrayList<>();
        jobPOList.add(jobPO);
        JobProfileVO jobProfileVO = POToProfileVO(jobPOList).get(0);
        return ApiResponse.ok(jobProfileVO);
    }

    public ApiResponse getRecommend(String openId, Integer posId) {
        SeekerIntentionPO seekerIntentionPO = seekerDao.getIntentionById(openId);
        SearchPO searchPO = new SearchPO();
        BeanUtils.copyProperties(seekerIntentionPO, searchPO);
        searchPO.setPosId(posId);
        List<JobProfileVO> jobProfileVOList = getRecList(searchPO);
        return ApiResponse.ok(jobProfileVOList);
    }

    public ApiResponse getResponsible(String openId) {
        List<JobPO> jobPOList = jobDao.getByHRId(openId);
        jobPOList.sort(new Comparator<JobPO>() {
            @Override
            public int compare(JobPO o1, JobPO o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        List<JobProfileVO> jobProfileVOList = POToProfileVO(jobPOList);
        return ApiResponse.ok(jobProfileVOList);
    }

    public ApiResponse search(SearchPO searchPO) {
        List<JobProfileVO> jobProfileVOList = getRecList(searchPO);
        jobProfileVOList.sort(new Comparator<JobProfileVO>() {
            @Override
            public int compare(JobProfileVO o1, JobProfileVO o2) {
                TextSimilarity textSimilarity = new CosineTextSimilarity();
                double score11 = textSimilarity.similarScore(o1.getTags(), searchPO.getTags());
                double score21 = textSimilarity.similarScore(o2.getTags(), searchPO.getTags());
                double score12 = textSimilarity.similarScore(o1.getName(), searchPO.getTags());
                double score22 = textSimilarity.similarScore(o2.getName(), searchPO.getTags());
                double score13 = textSimilarity.similarScore(o1.getCompany().getShortName(), searchPO.getTags());
                double score23 = textSimilarity.similarScore(o2.getCompany().getShortName(), searchPO.getTags());

                return (int) ((score21+score22+score23) * 10 - (score11+score12+score13) * 10);
            }
        });
        return ApiResponse.ok(jobProfileVOList);
    }

    private List<JobProfileVO> getRecList(SearchPO searchPO) {
        List<JobPO> jobPOList = jobDao.getRecommend(searchPO);
        jobPOList.sort(new Comparator<JobPO>() {
            @Override
            public int compare(JobPO o1, JobPO o2) {
                return o2.getMaxSalary() - o1.getMaxSalary();
            }
        });
        List<JobProfileVO> jobProfileVOList = POToProfileVO(jobPOList);
        return jobProfileVOList;
    }

    public List<JobProfileVO> POToProfileVO(List<JobPO> jobPOList) {
        List<JobProfileVO> jobProfileVOList = new ArrayList<>();
        for (JobPO jobPO : jobPOList) {
            JobProfileVO jobProfileVO = new JobProfileVO();
            BeanUtils.copyProperties(jobPO, jobProfileVO);
            HRPO hrLoginPO = jobDao.getHRByJobId(jobPO.getId());
            LoginVO loginVO = new LoginVO();
            BeanUtils.copyProperties(hrLoginPO, loginVO);
            CompanyPO companyPO = companyDao.getCompanyByHRId(loginVO.getOpenId());
            CompanyProfileVO companyProfileVO = new CompanyProfileVO();
            BeanUtils.copyProperties(companyPO, companyProfileVO);
            jobProfileVO.setCompany(companyProfileVO);
            jobProfileVO.setHrVO(loginVO);
            jobProfileVOList.add(jobProfileVO);
        }
        return jobProfileVOList;
    }

}
