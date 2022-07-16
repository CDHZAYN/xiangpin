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

    private LoginVO hrVO;

    private Integer id;

    private String name;

    private Integer posId;

    private Integer minSalary;

    private Integer maxSalary;

    private Integer salaryType;

    private String tags;


}
