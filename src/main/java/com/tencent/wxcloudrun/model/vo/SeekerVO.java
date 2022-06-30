package com.tencent.wxcloudrun.model.vo;

import com.tencent.wxcloudrun.model.po.seeker.SeekerPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerVO {
    public SeekerVO(SeekerPO seekerPO) {
        BeanUtils.copyProperties(seekerPO, this);
        this.birth = String.valueOf(seekerPO.getBirth().getTime());
    }

    private String openID;

    private String name;

    private String avatar;

    private String phone;

    private String month;

    private String year;

    private Boolean gender;

}
