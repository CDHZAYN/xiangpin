package com.tencent.wxcloudrun.controller.chatController;


import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.enums.MessageState;
import com.tencent.wxcloudrun.enums.MessageValue;
import com.tencent.wxcloudrun.model.vo.MessageVO;
import com.tencent.wxcloudrun.service.impl.MessageImpl;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@ServerEndpoint("/chatApi/{param}")
public class ChatController {

    private static final ConnectionPool connectionPool = new ConnectionPool();

    private String senderID;

    private int connectionID;

    private Session session;

    private static MessageImpl messageService;

    @Autowired
    public void setMessageService(MessageImpl service) {
        messageService = service;
    }

    public ChatController() {

    };

    /**
     * 初始化连接对象
     * @param param     包含发送者openID和接收者openID
     * @param session   可选参数
     */
    @OnOpen
    public void initConnection(@PathParam("param") String param, Session session) {
        senderID = param;

        this.session = session;

        connectionID = connectionPool.getDevices();

        connectionPool.push_back(senderID, this);

        String timeStamp = session.getQueryString().substring(10);

        List<MessageVO> messages = messageService.getMessagesByTime(senderID, timeStamp);

        System.out.println(timeStamp);

        if (messages.size() != 0) { // 说明有未接收到的消息
            for (MessageVO message : messages) {
                //messageService.setState(message.getId(), MessageState.NotRead);
                send(message, session);
            }
        }

        System.out.println(senderID + "上线了");
        System.out.println("当前连接用户数：" + connectionPool.getConnections() + "当前连接设备数：" + connectionPool.getDevices());
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        System.out.println("收到消息：" + message);

        JSONObject jsonObject = new JSONObject(message);

        if (Objects.equals(jsonObject.getString("message"), "")) {
            return;
        } // 为空消息则跳过处理


        MessageVO messageVO = new MessageVO();
        messageVO.setState(MessageState.values()[0]);
        // 初始化为未读
        messageVO.setMessageValue(MessageValue.values()[jsonObject.getInt("messageValue")]);

        messageVO.setSenderID(senderID);
        messageVO.setAcceptorID(jsonObject.getString("acceptorID"));
        messageVO.setMessage(jsonObject.getString("message"));
        messageVO.setSendTime(jsonObject.getString("sendTime"));

        if (messageVO.getMessageValue() == MessageValue.HasRead) { // 已读
            messageService.setHasRead(this.senderID, messageVO.getAcceptorID(), messageVO.getSendTime());
            hasReadBroadcast(messageVO);
            return;
        }

        System.out.println(senderID + "向" + messageVO.getAcceptorID() + "发送了一条消息");


        ArrayList<ChatController> accepterCharControllers = connectionPool.getConnection(messageVO.getAcceptorID());


        if (accepterCharControllers != null) {
            System.out.println(messageVO.getAcceptorID() + "的用户设备数：" + accepterCharControllers.size());
            for (ChatController accepterChatController : accepterCharControllers) {
                send(messageVO, accepterChatController.session);
            }
        }

        messageVO.setState(MessageState.NotRead);

        System.out.println("广播数：" + broadcast(messageVO));

        messageService.save(messageVO);
    }

    @OnClose
    public void onClose() {
        connectionPool.delete(senderID, this);
        System.out.println(senderID + "下线了");
    }

//    @OnError
//    public void onError() {
//
//    }

    public void send(MessageVO messageVO, Session session) {
        try {
            System.out.println(JSON.toJSONString(messageVO));
            session.getBasicRemote().sendText(JSON.toJSONString(messageVO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int broadcast(MessageVO messageVO) {
        ArrayList<ChatController> senderChatControllers = connectionPool.getConnection(messageVO.getSenderID());
        if (senderChatControllers == null) {
            return 0;
        }
        MessageVO messageVO1 = new MessageVO();
        BeanUtils.copyProperties(messageVO, messageVO1);
        messageVO1.setMessageValue(MessageValue.Broadcast);
        int size = 0;
        for (ChatController chatController : senderChatControllers) {
            if (chatController != this) {
                send(messageVO1, chatController.session);
                ++size;
            }
        }
        return size;
    }

    public int hasReadBroadcast(MessageVO messageVO) {
        ArrayList<ChatController> senderChatControllers = connectionPool.getConnection(messageVO.getSenderID());
        if (senderChatControllers == null) {
            return 0;
        }
        int size = 0;
        for (ChatController chatController : senderChatControllers) {
            if (chatController != this) {
                send(messageVO, chatController.session);
                ++size;
            }
        }
        return size;
    }
}
