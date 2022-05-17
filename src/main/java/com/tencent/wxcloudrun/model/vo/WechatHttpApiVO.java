package com.tencent.wxcloudrun.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WechatHttpApiVO {

    private String openID;

    private String sessionKey;

    private String unionID;

    private Integer errorCode;

    private String errMsg;
}
