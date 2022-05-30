package com.tencent.wxcloudrun.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HRRegisterDTO {

    private String openId;

    private String name;

    private String phoneNum;

    private boolean gender;

    private String avatarUrl;
}
