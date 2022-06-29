package com.tencent.wxcloudrun.model.vo.introduction;

import com.tencent.wxcloudrun.model.po.introduction.EducationalExperiencePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author 11731
 */
@Data
@Repository
@NoArgsConstructor
@AllArgsConstructor
public class EducationalExperienceVO {
    public EducationalExperienceVO(EducationalExperiencePO educationalExperiencePO) {
        this.timeStart = String.valueOf(educationalExperiencePO.getTimeStart().getTime());
        this.timerEnd = String.valueOf(educationalExperiencePO.getTimeEnd().getTime());
        this.school = educationalExperiencePO.getSchool();
        this.acaBg = educationalExperiencePO.getAcaBg();
    }

    String timeStart;

    String timerEnd;

    String school;

    Integer acaBg;
}
