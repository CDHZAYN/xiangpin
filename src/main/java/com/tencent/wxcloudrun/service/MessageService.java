package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.po.MessagePO;
import com.tencent.wxcloudrun.model.vo.MessageVO;

import java.util.Date;
import java.util.List;

public interface MessageService {
    void send(MessageVO messageVO);

    void save(MessageVO messageVO);

    List<MessageVO> getMessages(String acceptorID);

    List<MessageVO> getMessagesByTime(String acceptorID, String timeStamp);

    void setHasRead(String acceptorID, String senderID, String timeStamp);
}
