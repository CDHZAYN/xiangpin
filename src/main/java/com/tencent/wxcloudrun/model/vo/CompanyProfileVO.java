package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfileVO {

    private String id;

    private String shortName;

    private String avatar;

    private Integer industry;

    private Integer finance;

    private Integer scale;

}
