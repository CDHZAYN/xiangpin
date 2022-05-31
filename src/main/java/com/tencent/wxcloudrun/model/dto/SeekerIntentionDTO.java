package com.tencent.wxcloudrun.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerIntentionDTO {

    Integer expIndustry;

    Integer expMaxSalary;

    Integer expMinSalary;

    Integer jobType;

    String expJob;

}
