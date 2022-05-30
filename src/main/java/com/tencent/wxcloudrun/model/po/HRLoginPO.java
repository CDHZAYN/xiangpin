package com.tencent.wxcloudrun.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HRLoginPO {
    String userName;

    String userAvatar;

    String openID;
}
