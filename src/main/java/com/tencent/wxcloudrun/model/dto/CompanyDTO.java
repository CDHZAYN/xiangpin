package com.tencent.wxcloudrun.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private String fullName;

    private String shortName;

    private String id;

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

}
