package com.tencent.wxcloudrun.model.po.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerIntentionPO {

    private String openId;

    private String industry;

    private String job;

    private Integer jobType;

    private Integer maxSalary;

    private Integer minSalary;

    private Integer salaryType;

}
