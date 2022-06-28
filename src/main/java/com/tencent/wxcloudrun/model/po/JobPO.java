package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPO {

    private String companyID;

    private String HROpenID;

    private String jobName;

    // 职位类型
    private Integer expIndustry;

    private Integer expJob;

    private Integer expEducation;

    private String expExperience;

    // 职位分类
    private Integer jobType;

    // 工作地点
    private String position;

    private Integer posID;

    private Integer maxSalary;

    private Integer minSalary;

    private Integer salaryType;

    private String keywords;

    private String descContent;

    private String requestContent;



}
