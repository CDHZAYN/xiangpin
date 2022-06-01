package com.tencent.wxcloudrun.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    private String jobName;

    // 职位类型
    private String expIndustry;

    // 职位分类
    private String jobType;

    private int maxSalary;

    private int minSalary;

    private ArrayList<String> keywords;
}
