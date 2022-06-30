package com.tencent.wxcloudrun.model.vo.introduction;

import com.tencent.wxcloudrun.model.po.introduction.SupplementaryInformationPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

/**
 * @author 11731
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class SupplementaryInformationVO {
    public SupplementaryInformationVO(SupplementaryInformationPO supplementaryInformationPO) {
        BeanUtils.copyProperties(supplementaryInformationPO, this);
    }

    String otherExperiences;

    String skills;
}
