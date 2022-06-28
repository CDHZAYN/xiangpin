package com.tencent.wxcloudrun.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

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

    private ArrayList<String> keywords;

    private String descContent;

    private String requestContent;

}
