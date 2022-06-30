package com.tencent.wxcloudrun.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

    private String id;

    private String name;

    private String jobCate;

    private Integer education;

    private Integer experience;

    // 职位分类
    private Integer jobType;

    // 工作地点
    private String detailPos;

    private Integer posId;

    private Integer minSalary;

    private Integer maxSalary;

    private Integer salaryType;

    private String tags;

    private String describe;

    private String requirement;

}
