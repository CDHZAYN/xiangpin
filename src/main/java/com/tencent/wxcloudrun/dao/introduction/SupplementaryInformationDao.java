package com.tencent.wxcloudrun.dao.introduction;

import com.tencent.wxcloudrun.model.po.introduction.SupplementaryInformationPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 11731
 */
@Mapper
public interface SupplementaryInformationDao {
    int deleteSupplementaryInformationByOpenId(String openId);

    int insertSupplementaryInformation(String openId, SupplementaryInformationPO supplementaryInformationPo);

    List<SupplementaryInformationPO> querySupplementaryInformationByOpenId(String openId);
}
