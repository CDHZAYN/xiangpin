package com.tencent.wxcloudrun.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {

    public String userName;

    public String userAvatar;

}