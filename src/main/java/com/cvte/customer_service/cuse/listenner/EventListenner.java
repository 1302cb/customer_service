package com.cvte.customer_service.cuse.listenner;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.cvte.customer_service.cuse.cache.ClientCache;
import com.cvte.customer_service.cuse.clause.ChatMessage;
import com.cvte.customer_service.cuse.model.ChatData;
import com.cvte.customer_service.cuse.service.BotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;



@Component
public class EventListenner {
    private static Logger logger = LoggerFactory.getLogger(EventListenner.class);

    @Resource
    private ClientCache clientCache;

    @Autowired
    private BotService botService;

    /**
     * 客户端连接
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        logger.info("client->"+client);
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        logger.info("onConnect userId:"+userId);
        UUID sessionId = client.getSessionId();
        clientCache.saveClient(userId,sessionId,client);
        logger.info("建立连接");
    }

    /**
     * 客户端断开
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("disconnect->"+client);
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        clientCache.deleteSessionClient(userId,client.getSessionId());
        logger.info("关闭连接");
    }


    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    @OnEvent("messageevent")
    public void onEvent(SocketIOClient client, AckRequest request, ChatData data) {
        logger.info("data->"+data);
        ChatData chat = new ChatData();
        //此处加入语意处理的逻辑
        ChatMessage chatMessage = botService.chat(data.getMessage());
        logger.info("chatMessage->"+chatMessage.toString());
        chat.setUserName(chatMessage.getSession_id());
        chat.setMessage(chatMessage.getTextMessage());
        client.sendEvent("message",chat);
    }

}