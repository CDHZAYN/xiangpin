package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.MessageDao;
import com.tencent.wxcloudrun.enums.MessageState;
import com.tencent.wxcloudrun.enums.MessageValue;
import com.tencent.wxcloudrun.model.po.MessagePO;
import com.tencent.wxcloudrun.model.vo.MessageVO;
import com.tencent.wxcloudrun.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageImpl implements MessageService {

    private final MessageDao messageDao;

    @Autowired
    public MessageImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void setState(int id, MessageState state) {
        messageDao.setMessageStateByID(id, state.ordinal());
    };

    @Override
    public void send(MessageVO messageVO) {

    }

    @Override
    public void save(MessageVO messageVO) {
        MessagePO messagePO = new MessagePO();

        messagePO.setId(messageVO.getId());
        messagePO.setMessage(messageVO.getMessage());
        messagePO.setSenderID(messageVO.getSenderID());
        messagePO.setAcceptorID(messageVO.getAcceptorID());
        messagePO.setSendTime(messageVO.getSendTime());

        messagePO.setMessageValue(messageVO.getMessageValue().ordinal());
        messagePO.setState(messageVO.getState().ordinal());

        messageDao.addMessage(messagePO);
    }

    @Override
    public List<MessageVO> getMessages(String acceptorID) {
        List<MessagePO> messagePOS = messageDao.selectMessageByAccepterID(acceptorID);
        List<MessageVO> messageVOS = new ArrayList<>();

        for (MessagePO messagePO : messagePOS) {
            MessageVO messageVO = new MessageVO();
            messageVO.setId(messagePO.getId());
            messageVO.setMessage(messagePO.getMessage());
            messageVO.setSenderID(messagePO.getSenderID());
            messageVO.setAcceptorID(messagePO.getAcceptorID());
            messageVO.setSendTime(messagePO.getSendTime());

            messageVO.setMessageValue(MessageValue.values()[messagePO.getMessageValue()]);
            messageVO.setState(MessageState.values()[messagePO.getState()]);
            messageVOS.add(messageVO);
        }

        return messageVOS;
    }
}
