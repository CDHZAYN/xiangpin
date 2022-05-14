package com.xiangpin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerVO {

    private String openID;

    private String name;

    private String phone;

    private Integer old;

    private String Education;

    private String birth;

    private Boolean gender;

}
