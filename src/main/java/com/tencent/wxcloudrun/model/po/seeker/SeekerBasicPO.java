package com.tencent.wxcloudrun.model.po.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerBasicPO {

    private String openID;

    private String name;

    private String phone;

    private Integer old;

    private String education;

    private Date birth;

    private Boolean gender;

}
