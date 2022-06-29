package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyPO {

    private String id;

    private String fullName;

    private String shortName;

    private Integer industry;

    private String avatar;

    private String license;

    //人员规模
    private Integer scale;

    private Integer finance;

    private String intro;

    private Integer posID;

    private String website;

    private String position;

    private String keywords;

}
