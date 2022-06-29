package com.tencent.wxcloudrun.model.po.introduction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author 11731
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class SupplementaryInformationPO {
    String otherExperiences;

    String skills;
}
