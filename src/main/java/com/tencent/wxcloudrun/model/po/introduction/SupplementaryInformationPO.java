package com.tencent.wxcloudrun.model.po.introduction;

import com.tencent.wxcloudrun.model.vo.introduction.SupplementaryInformationVO;
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
public class SupplementaryInformationPO {
    public SupplementaryInformationPO(SupplementaryInformationVO supplementaryInformationVO) {
        BeanUtils.copyProperties(supplementaryInformationVO, this);
    }

    String otherExperiences;

    String skills;
}
