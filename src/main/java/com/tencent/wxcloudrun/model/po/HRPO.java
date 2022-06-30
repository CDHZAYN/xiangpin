package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HRPO {
    private String openId;

    private String name;

    private String phone;

    private String gender;

    private String avatar;
}
