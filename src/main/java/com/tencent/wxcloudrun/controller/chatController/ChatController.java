package com.tencent.wxcloudrun.controller.chatController;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/chatApi")
public class ChatController {
    @OnMessage
    public void handleMessage(Session session, String message) throws IOException {
        System.out.println("服务器收到的消息：" + message);
        session.getBasicRemote().sendText("收到！");
    }

}
