package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.SeekerDao;
import com.tencent.wxcloudrun.dao.introduction.EducationExperienceDao;
import com.tencent.wxcloudrun.dao.introduction.SupplementaryInformationDao;
import com.tencent.wxcloudrun.dao.introduction.WorkExperienceDao;
import com.tencent.wxcloudrun.model.dto.IntroductionDTO;
import com.tencent.wxcloudrun.model.dto.seeker.SeekerIntentionDTO;
import com.tencent.wxcloudrun.model.po.introduction.EducationalExperiencePO;
import com.tencent.wxcloudrun.model.po.introduction.SupplementaryInformationPO;
import com.tencent.wxcloudrun.model.po.introduction.WorkExperiencePO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import com.tencent.wxcloudrun.model.po.seeker.SeekerPO;
import com.tencent.wxcloudrun.model.vo.introduction.EducationalExperienceVO;
import com.tencent.wxcloudrun.model.vo.introduction.SupplementaryInformationVO;
import com.tencent.wxcloudrun.model.vo.introduction.WorkExperienceVO;
import com.tencent.wxcloudrun.model.vo.SeekerVO;
import com.tencent.wxcloudrun.service.IntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 11731
 */
@Service
public class IntroductionServiceImpl implements IntroductionService {
    private final SeekerDao seekerDao;

    private final WorkExperienceDao workExperienceDao;

    private final SupplementaryInformationDao supplementaryInformationDao;

    private final EducationExperienceDao educationalExperienceDao;

    @Autowired
    IntroductionServiceImpl(WorkExperienceDao workExperienceDao, SupplementaryInformationDao supplementaryInformationDao,
                            EducationExperienceDao educationalExperienceDao, SeekerDao seekerDao) {
        this.educationalExperienceDao = educationalExperienceDao;
        this.workExperienceDao = workExperienceDao;
        this.supplementaryInformationDao = supplementaryInformationDao;
        this.seekerDao = seekerDao;
    }

    @Override
    public IntroductionDTO queryIntroductionByOpenId(String openId) {
        SeekerPO seekerPO = seekerDao.getSeeker(openId);
        List<EducationalExperiencePO> educationalExperiencePos = educationalExperienceDao.queryEducationalExperiencePOByOpenId(openId);
        SeekerIntentionPO seekerIntentionPO = seekerDao.getIntentionById(openId);
        SupplementaryInformationPO supplementaryInformationPO = supplementaryInformationDao.querySupplementaryInformationByOpenId(openId);
        List<WorkExperiencePO> workExperiencePos = workExperienceDao.queryWorkExperiencesByOpenId(openId);

        SeekerVO seekerVO = new SeekerVO(seekerPO);
        List<EducationalExperienceVO> educationalExperienceVos = new ArrayList<>();
        for (EducationalExperiencePO educationalExperiencePO : educationalExperiencePos) {
            educationalExperienceVos.add(new EducationalExperienceVO(educationalExperiencePO));
        }
        SupplementaryInformationVO supplementaryInformationVO = new SupplementaryInformationVO(supplementaryInformationPO);
        SeekerIntentionDTO seekerIntentionDTO = new SeekerIntentionDTO(seekerIntentionPO);
        List<WorkExperienceVO> workExperienceVOS = new ArrayList<>();
        for (WorkExperiencePO experiencePO : workExperiencePos) {
            workExperienceVOS.add(new WorkExperienceVO(experiencePO));
        }

        return new IntroductionDTO(seekerVO, educationalExperienceVos,
                seekerIntentionDTO, supplementaryInformationVO, workExperienceVOS);
    }

    @Override
    public boolean deleteIntroductionByOpenId(String openId) {
        return workExperienceDao.deleteWorkExperiencesByOpenId(openId) > 0 &&
                educationalExperienceDao.deleteEducationExperiencesByOpenId(openId) > 0 &&
                supplementaryInformationDao.deleteSupplementaryInformationByOpenId(openId) > 0;
    }

    @Override
    public IntroductionDTO createIntroduction(IntroductionDTO introductionDTO, String openId) {
        deleteIntroductionByOpenId(openId);

        for (EducationalExperienceVO educationalExperienceVO : introductionDTO.getExperienceList()) {
            educationalExperienceDao.insertEducationExperience(openId, new EducationalExperiencePO(educationalExperienceVO));
        }
        supplementaryInformationDao.insertSupplementaryInformation(openId,
                new SupplementaryInformationPO(introductionDTO.getSupplementInformation()));

        for (WorkExperienceVO workExperienceVO : introductionDTO.getWorkExperienceList()) {
            workExperienceDao.insertWorkExperience(openId, new WorkExperiencePO(workExperienceVO));
        }

        return queryIntroductionByOpenId(openId);
    }

    @Override
    public IntroductionDTO updateIntroduction(IntroductionDTO introductionDTO, String openId) {
        deleteIntroductionByOpenId(openId);
        createIntroduction(introductionDTO, openId);
        return queryIntroductionByOpenId(openId);
    }
}
