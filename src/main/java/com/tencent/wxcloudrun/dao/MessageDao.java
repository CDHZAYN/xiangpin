package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.po.MessagePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface MessageDao {

    void addMessage(MessagePO messagePO);

    List<MessagePO> selectMessageByAccepterID(String acceptorID);

    void setMessageStateByID(int id, int state);

    List<MessagePO> selectMessageByAccepterIDAndTime(String acceptorID, Date timeStamp);

    void updateMessageValue(String senderID, String acceptorID, Date timeStamp);
}
