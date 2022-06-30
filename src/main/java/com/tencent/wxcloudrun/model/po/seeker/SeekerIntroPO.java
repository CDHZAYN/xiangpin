package com.tencent.wxcloudrun.model.po.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerIntroPO {

    private String openId;

    private String educationSchool;

    private String educationDegree;

    private Date educationInSchoolTime;

    private Date educationGraduateTime;

    private String mainExperience;

    private String skill;

    private String specialties;

    private String works;

    private String direction;

    private String personal;

    private String jobs;

}
