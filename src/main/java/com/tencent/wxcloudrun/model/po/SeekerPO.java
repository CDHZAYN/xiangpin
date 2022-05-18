package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerPO {

    private String openID;

    private String name;

    private String phone;

    private Integer old;

    private String education;

    private String birth;

    private Boolean gender;

}
