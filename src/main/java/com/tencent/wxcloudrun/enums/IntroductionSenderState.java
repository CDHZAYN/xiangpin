package com.tencent.wxcloudrun.enums;

public enum IntroductionSenderState {

    HasSend,

    WaitForResult,

    Success,

    Failure;

    /**
     *
     * @param state
     * @return
     */
    public String valueToString(IntroductionSenderState state) {
        return state.name();
    }

    /**
     *
     * @param str 要转为枚举类的字段名称
     * @return 转换后的枚举类
     */
    public IntroductionSenderState stringToValue(String str) {
        return IntroductionSenderState.valueOf(str);
    }
}
