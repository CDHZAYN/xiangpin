package com.tencent.wxcloudrun.model.vo.introduction;

import com.tencent.wxcloudrun.model.po.introduction.WorkExperiencePO;
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
public class WorkExperienceVO {
    public WorkExperienceVO(WorkExperiencePO workExperiencePO) {
        try {
            BeanUtils.copyProperties(workExperiencePO, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.timeEnd = String.valueOf(workExperiencePO.getTimeEnd().getTime());
        this.timeStart = String.valueOf(workExperiencePO.getTimeStart().getTime());
    }

    String company;

    String jobName;

    @Nullable
    String supplement;

    String timeEnd;

    String timeStart;
}
