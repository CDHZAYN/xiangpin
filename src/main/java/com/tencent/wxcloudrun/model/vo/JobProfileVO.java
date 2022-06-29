package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileVO {

    private CompanyProfileVO company;

    private String HRName;

    private Integer id;

    private String jobName;

    private Integer city;

    private Integer salaryFloor;

    private Integer salaryRoof;

    private Integer salaryType;

    private String tags;
}
