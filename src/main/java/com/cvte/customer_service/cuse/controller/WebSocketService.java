package com.cvte.customer_service.cuse.controller;

import com.cvte.customer_service.cuse.service.BotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/chatSocket/{uid}")
public class WebSocketService {
    private static Logger logger = LoggerFactory.getLogger(WebSocketService.class);


    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketService> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收sid
     */
    private String uid = "";

    @Autowired
    private BotService botService;

    /**
     * 打开连接，需要将Session保存下来，为了分辨是哪一个对话
     */
    @OnOpen
    public void openSocket(Session session, @PathParam("uid") String uid) {
        logger.info("收到连接消息");
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        logger.info("有新窗口开始监听:" + uid);
        this.uid = uid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            logger.error("websocket IO异常");
        }
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
        logger.info("有一连接关闭");
    }

    /**
     * 主动发送消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口" + uid + "的信息:" + message);
        //在这里根据message调用chat来进行解析
        String res = botService.chat(message).getTextMessage();
        //再将消息返回回去
        try {
            sendMessage(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
