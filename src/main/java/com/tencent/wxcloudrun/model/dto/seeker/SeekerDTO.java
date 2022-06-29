package com.tencent.wxcloudrun.model.dto.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeekerDTO {

    private String name;

    private String avatar;

    private String phone;

    private String month;

    private String year;

    private String gender;
}
