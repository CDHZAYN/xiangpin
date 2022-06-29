package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPO {

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
    private Integer salaryType;//?
    private String tags;
    private String describe;
    private String requirement;

}
