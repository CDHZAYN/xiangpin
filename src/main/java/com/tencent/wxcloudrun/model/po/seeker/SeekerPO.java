package com.tencent.wxcloudrun.model.po.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerPO {

    private String openId;

    private String name;

    private String avatar;

    private String phone;

    private String month;

    private String year;

    private String gender;

}
