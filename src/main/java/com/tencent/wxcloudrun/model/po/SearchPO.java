package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPO {

    String industry;

    String job;

    Integer maxSalary;

    Integer minSalary;

    Integer salaryType;

    Integer jobType;

    Integer posId;

    String tags;

}
