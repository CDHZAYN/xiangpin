package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobVO {
    private String jobName;

    private HRLoginVO hrLoginVO;

    // 职位类型
    private String expJob;

    // 职位分类
    private String jobType;

    // 工作地点
    private String workPosition;

    private int maxSalary;

    private int minSalary;

    private String descContent;

    private String requestContent;

    private CompanyVO company;

    private ArrayList<String> keywords;
}
