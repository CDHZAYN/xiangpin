package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntentionPO {
    String openId;

    int intentionId;

    String expIndustry;

    int expMaxSalary;

    int expMinSalary;

    String jobType;
}
