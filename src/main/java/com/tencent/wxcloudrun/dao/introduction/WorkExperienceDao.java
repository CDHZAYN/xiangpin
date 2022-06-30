package com.tencent.wxcloudrun.dao.introduction;

import com.tencent.wxcloudrun.model.po.introduction.EducationalExperiencePO;
import com.tencent.wxcloudrun.model.po.introduction.WorkExperiencePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 11731
 */
@Mapper
public interface WorkExperienceDao {
    int deleteWorkExperiencesByOpenId(String openId);

    int insertWorkExperience(String openId, WorkExperiencePO workExperiencePO);

    List<WorkExperiencePO> queryWorkExperiencesByOpenId(String openId);
}
