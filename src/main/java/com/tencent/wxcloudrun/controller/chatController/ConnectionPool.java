package com.tencent.wxcloudrun.controller.chatController;


import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConnectionPool {
    private final Map<String, ArrayList<ChatController>> connectionPool = new HashMap<>();

    @Getter
    private int connections = 0;

    @Getter
    private int devices = 0;

    public void push_back(String openId, ChatController chatController) {
        if (connectionPool.get(openId) != null) {
            connectionPool.get(openId).add(chatController);
        } else {
            ArrayList<ChatController> temp = new ArrayList<>();
            temp.add(chatController);
            connectionPool.put(openId, temp);
            ++connections;
        }
        ++devices;
    }

    public void delete(String openId, ChatController chatController) {
        ArrayList<ChatController> temp = connectionPool.get(openId);
        if (temp == null) {
            throw new UnsupportedOperationException("不存在该连接");
        }
        if (!temp.contains(chatController)) {
            throw new UnsupportedOperationException("不存在该连接");
        } else {
            temp.remove(chatController);
            --devices;
            if (temp.size() == 0) {
                connectionPool.remove(openId);
                --connections;
            }
        }
    }

    public ArrayList<ChatController> getConnection(String openId) {
        return connectionPool.get(openId);
    }
}
