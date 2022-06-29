package com.tencent.wxcloudrun.model.po.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerIntentionPO {
    String openID;

    Integer intentionID;

    String expIndustry;

    String expJob;

    int expMaxSalary;

    int expMinSalary;

    Integer SalaryType;

    String jobType;

}
