package com.tencent.wxcloudrun.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private String acaBg;

    private String avatarUrl;

    private String gender;

    private String month;

    private String name;

    private String phoneNum;

    private String year;
}
