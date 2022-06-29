package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPO {

    Integer expIndustry;

    String expJob;

    Integer expMaxSalary;

    Integer expMinSalary;

    Integer SalaryType;

    Integer jobType;

    Integer city;

    String keywords;

}
