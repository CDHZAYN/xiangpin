package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {

    public String openId;

    public String name;

    public String avatar;
}
