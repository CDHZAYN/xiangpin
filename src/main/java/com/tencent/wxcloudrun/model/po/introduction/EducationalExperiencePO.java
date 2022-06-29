package com.tencent.wxcloudrun.model.po.introduction;

import com.tencent.wxcloudrun.model.vo.introduction.EducationalExperienceVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author 11731
 */
@Data
@Repository
@NoArgsConstructor
@AllArgsConstructor
public class EducationalExperiencePO {
    public EducationalExperiencePO(EducationalExperienceVO educationalExperienceVO) {
        this.timeStart = new Date(Long.parseLong(educationalExperienceVO.getTimeStart()));
        this.timeEnd = new Date(Long.parseLong(educationalExperienceVO.getTimerEnd()));
        this.school = educationalExperienceVO.getSchool();
        this.acaBg = educationalExperienceVO.getAcaBg();
    }

    Date timeStart;

    Date timeEnd;

    String school;

    Integer acaBg;
}
