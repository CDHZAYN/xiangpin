package com.tencent.wxcloudrun.model.vo.introduction;

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
public class SupplementaryInformationVO {
    String otherExperiences;

    String skills;
}
