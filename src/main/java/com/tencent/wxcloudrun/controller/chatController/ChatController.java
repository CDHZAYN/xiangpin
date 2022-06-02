package com.tencent.wxcloudrun.controller.chatController;


import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.enums.MessageState;
import com.tencent.wxcloudrun.enums.MessageValue;
import com.tencent.wxcloudrun.model.vo.MessageVO;
import com.tencent.wxcloudrun.service.impl.MessageImpl;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@ServerEndpoint("/chatApi/{param}")
public class ChatController {

    private static Map<String, ChatController> connectionPool = new ConcurrentHashMap<>();

    private static int onLineCount = 0; // 记录在线人数

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

        connectionID = onLineCount;
        ++onLineCount;

        connectionPool.put(senderID, this);

        List<MessageVO> messages = messageService.getMessages(senderID);

        if (messages.size() != 0) { // 说明有未接收到的消息
            for (MessageVO message : messages) {
                messageService.setState(message.getId(), MessageState.NotRead);
                send(message);
            }
        }

        System.out.println(senderID + "上线了");
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
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

        System.out.println(senderID + "向" + messageVO.getAcceptorID() + "发送了一条消息");

        ChatController accepterCharController = connectionPool.get(messageVO.getAcceptorID());
        if (accepterCharController != null) { //说明接收者在线
            send(messageVO);
            messageVO.setState(MessageState.NotRead);
            // 消息状态变为已发送
        }
        messageService.save(messageVO);
    }

    @OnClose
    public void onClose() {
        --onLineCount;
        connectionPool.remove(senderID);
    }

//    @OnError
//    public void onError() {
//
//    }

    public void send(MessageVO messageVO) {
        try {
            System.out.println(JSON.toJSONString(messageVO));
            this.session.getBasicRemote().sendText(JSON.toJSONString(messageVO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void newConnectionSend(MessageVO messageVO) {
//        if (Objects.equals(messageVO.getSenderID(), senderID)) { // 如果发送方是自己，直接发送
//            try {
//                this.session.getBasicRemote().sendText(JSON.toJSONString(messageVO));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else if (connectionPool.get(messageVO.getSenderID()) != null) { // 发送方不是自己且在线
//            ChatController accepterCharController = connectionPool.get(messageVO.getAcceptorID());
//            accepterCharController.send(messageVO);
//        } else { // 发送方不是自己且不在线
//            connectionPool.put(messageVO.getSenderID(), new ChatController());
//
//        }
//    }
}
