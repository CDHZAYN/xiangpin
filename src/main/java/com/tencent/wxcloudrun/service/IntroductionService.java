package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.dto.IntroductionDTO;

/**
 * @author 11731
 */
public interface IntroductionService {
    /**
     * 根据OpenId查找简历信息
     * @param openId 要查询的简历OpenId
     * @return 查找到的简历信息
     */
    IntroductionDTO queryIntroductionByOpenId(String openId);

    /**
     * 根据openId删除简历
     * @return 是否删除成功
     */
    boolean deleteIntroductionByOpenId(String openId);

    /**
     * 创建新简历
     * @param introductionDTO 要创建的简历
     * @return 创建好的新简历
     */
    IntroductionDTO createIntroduction(IntroductionDTO introductionDTO, String openId);

    /**
     * 更新简历
     * @param introductionDTO 更新的简历信息
     * @return 更新后的简历信息
     */
    IntroductionDTO updateIntroduction(IntroductionDTO introductionDTO, String openId);
}
