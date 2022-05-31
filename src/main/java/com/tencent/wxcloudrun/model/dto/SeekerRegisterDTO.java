package com.tencent.wxcloudrun.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerRegisterDTO {

    private SeekerBasicDTO basic;

    private List<SeekerIntentionDTO> intention;

}
