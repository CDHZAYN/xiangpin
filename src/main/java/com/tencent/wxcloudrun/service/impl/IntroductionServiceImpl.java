package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.dto.IntroductionDTO;
import com.tencent.wxcloudrun.service.IntroductionService;
import org.springframework.stereotype.Service;

/**
 * @author 11731
 */
@Service
public class IntroductionServiceImpl implements IntroductionService {

    @Override
    public IntroductionDTO queryIntroductionByOpenId(String openId) {
        return null;
    }

    @Override
    public boolean deleteIntroductionByOpenId() {
        return false;
    }

    @Override
    public IntroductionDTO createIntroduction(IntroductionDTO introductionDTO) {
        return null;
    }

    @Override
    public IntroductionDTO updateIntroduction(IntroductionDTO introductionDTO) {
        return null;
    }
}
