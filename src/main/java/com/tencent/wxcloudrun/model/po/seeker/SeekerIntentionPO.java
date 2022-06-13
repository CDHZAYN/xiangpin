package com.tencent.wxcloudrun.model.po.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerIntentionPO {
    String openID;

    int intentionID;

    String expIndustry;

    int expMaxSalary;

    int expMinSalary;

    Integer SalaryType;

    String jobType;

    String expJob;
}
