package com.tencent.wxcloudrun.model.po;

import com.tencent.wxcloudrun.enums.MessageState;
import com.tencent.wxcloudrun.enums.MessageValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePO {
    private int id;

    private int state;

    // 消息类型枚举类
    private int messageValue;

    private String senderID;

    private String acceptorID;

    // 消息内容（根据messageValue而定，可能是url可能是文本）
    private String message;

    private String sendTime;
}
