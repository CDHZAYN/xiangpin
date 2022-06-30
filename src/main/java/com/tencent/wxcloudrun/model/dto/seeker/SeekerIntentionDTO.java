package com.tencent.wxcloudrun.model.dto.seeker;

import com.tencent.wxcloudrun.model.po.seeker.SeekerIntentionPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerIntentionDTO {
    public SeekerIntentionDTO(SeekerIntentionPO seekerIntentionPO) {
        BeanUtils.copyProperties(seekerIntentionPO, this);
    }

    private Integer industry;

    private String job;

    private Integer jobType;

    private Integer maxSalary;

    private Integer minSalary;

    private Integer salaryType;

}
