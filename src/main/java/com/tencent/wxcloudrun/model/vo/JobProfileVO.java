package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileVO {

    private String companyShortName;

    private String HRName;

    private String jobName;

    // 职位类型
    private Integer expIndustry;

    private Integer expJob;

    // 职位分类
    private Integer jobType;

    // 工作地点
    private String position;

    private Integer maxSalary;

    private Integer minSalary;

    private Integer salaryType;

    private ArrayList<String> keywords;
}
