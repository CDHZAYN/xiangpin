package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerVO {

    private String openID;

    private String name;

    private String avatar;

    private String phone;

    private String month;

    private String year;

    private Boolean gender;

}
