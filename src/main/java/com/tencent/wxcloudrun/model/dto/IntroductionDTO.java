package com.tencent.wxcloudrun.model.dto;

import com.tencent.wxcloudrun.model.dto.seeker.SeekerIntentionDTO;
import com.tencent.wxcloudrun.model.vo.introduction.EducationalExperienceVO;
import com.tencent.wxcloudrun.model.vo.introduction.SupplementaryInformationVO;
import com.tencent.wxcloudrun.model.vo.introduction.WorkExperienceVO;
import com.tencent.wxcloudrun.model.vo.SeekerVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 11731
 */
@Data
@Repository
@NoArgsConstructor
@AllArgsConstructor
public class IntroductionDTO {

    SeekerVO seeker;

    List<EducationalExperienceVO> experienceList;

    SeekerIntentionDTO intention;

    SupplementaryInformationVO supplementInformation;

    List<WorkExperienceVO> workExperienceList;
}
