package com.tencent.wxcloudrun.model.dto.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerIntentionDTO {

    Integer expIndustry;

    String expJob;

    Integer expMaxSalary;

    Integer expMinSalary;

    Integer SalaryType;

    Integer jobType;

}
