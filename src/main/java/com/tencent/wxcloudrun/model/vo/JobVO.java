package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobVO {

    private CompanyProfileVO company;

    private HRLoginVO hrLoginVO;

    private Integer id;

    private String jobName;

    private String jobCate;

    private Integer academicRequirement;

    private Integer ExperienceRequirement;

    // 职位分类
    private Integer jobType;

    // 工作地点
    private String detailPos;

    private Integer city;

    private Integer salaryFloor;

    private Integer salaryRoof;

    private Integer salaryType;

    private String tags;

    private String describe;

    private String requirement;
}
