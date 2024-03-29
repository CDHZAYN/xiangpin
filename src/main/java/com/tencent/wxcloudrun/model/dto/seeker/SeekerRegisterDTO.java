package com.tencent.wxcloudrun.model.dto.seeker;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerRegisterDTO {

    private SeekerDTO seeker;

    private SeekerIntentionDTO intention;

}
