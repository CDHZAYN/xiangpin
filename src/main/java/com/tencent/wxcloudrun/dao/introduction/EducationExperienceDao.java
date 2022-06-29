package com.tencent.wxcloudrun.dao.introduction;

import com.tencent.wxcloudrun.model.po.introduction.EducationalExperiencePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 11731
 */
@Mapper
@Repository
public interface EducationExperienceDao {
    int deleteEducationExperiencesByOpenId(String openId);

    int insertEducationExperience(String openId, EducationalExperiencePO educationalExperiencePo);

    List<EducationalExperiencePO> queryEducationalExperiencePOByOpenId(String openId);
}
