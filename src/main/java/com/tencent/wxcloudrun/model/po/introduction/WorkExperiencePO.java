package com.tencent.wxcloudrun.model.po.introduction;

import com.tencent.wxcloudrun.model.vo.introduction.WorkExperienceVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author 11731
 */
@Data
@Repository
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperiencePO {
    public WorkExperiencePO(WorkExperienceVO workExperienceVO) {
        try {
            BeanUtils.copyProperties(workExperienceVO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.timeEnd = new Date(Long.parseLong(workExperienceVO.getTimeEnd()));
        this.timeStart = new Date(Long.parseLong(workExperienceVO.getTimeStart()));
    }

    String company;

    String jobName;

    @Nullable
    String supplement;

    Date timeEnd;

    Date timeStart;
}
