package com.tencent.wxcloudrun.model.po.seeker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeekerLoginPO {
    String userName;

    String userAvatar;

    String openID;
}
