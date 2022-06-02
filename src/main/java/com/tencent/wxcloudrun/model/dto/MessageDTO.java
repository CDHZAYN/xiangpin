package com.tencent.wxcloudrun.model.dto;

import com.tencent.wxcloudrun.enums.MessageState;
import com.tencent.wxcloudrun.enums.MessageValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    // 消息类型枚举类
    private MessageValue messageValue;

    private String acceptorID;

    // 消息内容（根据messageValue而定，可能是url可能是文本）
    private String message;

    private String sendTime;
}
