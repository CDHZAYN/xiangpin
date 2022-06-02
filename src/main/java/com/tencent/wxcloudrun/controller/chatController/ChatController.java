package com.tencent.wxcloudrun.controller.chatController;


import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.enums.MessageState;
import com.tencent.wxcloudrun.enums.MessageValue;
import com.tencent.wxcloudrun.model.dto.MessageDTO;
import org.json.JSONObject;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;


@ServerEndpoint("/chatApi/{param}")
public class ChatController {

    private static Map<String, ChatController> connectionPool = new ConcurrentHashMap<>();

    private static int onLineCount = 0; // 记录在线人数

    private String senderID;

    private int connectionID;

    private Session session;



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

        System.out.println(senderID + "上线了");
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        JSONObject jsonObject = new JSONObject(message);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setState(MessageState.values()[jsonObject.getInt("state")]);
        messageDTO.setMessageValue(MessageValue.values()[jsonObject.getInt("messageValue")]);

        messageDTO.setSenderID(jsonObject.getString("senderID"));
        messageDTO.setAcceptedID(jsonObject.getString("accepterID"));
        messageDTO.setMessage(jsonObject.getString("message"));
        messageDTO.setSendTime(jsonObject.getString("sendTime"));

        System.out.println(senderID + "向" + messageDTO.getAcceptedID() + "发送了一条消息");

        ChatController accepterCharController = connectionPool.get(messageDTO.getAcceptedID());
        if (accepterCharController == null) { //说明接收者不在线
            //TODO
            // 存入数据库
        } else {
            accepterCharController.send(messageDTO);
        }
    }

    @OnClose
    public void onClose() {
        --onLineCount;

        connectionPool.remove(senderID);
    }

    public void send(MessageDTO messageDTO) {
        try {
            System.out.println(JSON.toJSONString(messageDTO));
            this.session.getBasicRemote().sendText(JSON.toJSONString(messageDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
