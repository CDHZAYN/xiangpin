package com.xiangpin.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeekerPO {

    private String openID;

    private String name;

    private String phone;

    private Integer old;

//    private String Education;

    private String birth;

    private Boolean gender;

}
