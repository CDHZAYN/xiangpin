package com.tencent.wxcloudrun.model.po.seeker;

import com.tencent.wxcloudrun.model.vo.SeekerVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerPO {
    public SeekerPO(SeekerVO seekerVO) {
        BeanUtils.copyProperties(seekerVO, this);
    }

    private String openId;

    private String name;

    private String avatar;

    private String phone;

    private String month;

    private String year;

    private String gender;

}
