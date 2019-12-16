package com.cvte.customer_service.cuse.vo;

import com.cvte.customer_service.cuse.clause.ChatMessage;

import java.io.Serializable;
import java.util.Date;

public class ChatMessageVO implements Serializable {
    private static final long serialVersionUID = -5143600160993126898L;
    private Integer id;
    private String sessionId;
    private String chatBotId;
    private String type;
    private String sender;
    private String receiver;
    private Date createTime;
    private String textMessage;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getChatBotId() {
        return chatBotId;
    }

    public void setChatBotId(String chatBotId) {
        this.chatBotId = chatBotId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public static ChatMessageVO fromChatMessage(ChatMessage message) {
        ChatMessageVO result = new ChatMessageVO();
        result.chatBotId = message.getChatbotID();
        result.createTime = new Date();
        result.sender = message.getSender();
        result.receiver = message.getReceiver();
        result.sessionId = message.getSession_id();
        result.textMessage = message.getTextMessage();
        result.type = message.getType();
        return result;
    }

    @Override
    public String toString() {
        return "ChatMessageVO{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", chatBotId='" + chatBotId + '\'' +
                ", type=" + type +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", createTime=" + createTime +
                ", textMessage='" + textMessage + '\'' +
                '}';
    }
}
