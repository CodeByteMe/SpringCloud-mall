package com.mall.pay.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/webSocket/{orderId}")
public class WebSocket {

    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void open(@PathParam("orderId") String orderId, Session session){
        sessionMap.put(orderId,session);
    }

    @OnClose
    public void close(@PathParam("orderId")String orderId){
        sessionMap.remove(orderId);
    }


    public static void sendMessage(String orderId,String msg) throws IOException {
        Session session = sessionMap.get(orderId);
        session.getBasicRemote().sendText(msg);
    }

}
