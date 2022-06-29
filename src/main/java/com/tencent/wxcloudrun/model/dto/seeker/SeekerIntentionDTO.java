package com.tencent.wxcloudrun.model.dto.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerIntentionDTO {

    private Integer industry;

    private String job;

    private Integer jobType;

    private Integer maxSalary;

    private Integer minSalary;

    private Integer salaryType;

}
