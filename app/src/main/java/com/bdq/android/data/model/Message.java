package com.bdq.android.data.model;

/**
 * 消息数据实体（Model�? * 对应一条聊天消�? */
public class Message {
    private String id;
    private String conversationId;  // 所属会话ID
    private String content;         // 消息内容
    private boolean isUser;         // 是否是用户发送的（true=用户，false=AI�?    private long timestamp;         // 发送时�?
    public Message(String id, String conversationId, String content, boolean isUser, long timestamp) {
        this.id = id;
        this.conversationId = conversationId;
        this.content = content;
        this.isUser = isUser;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public String getContent() {
        return content;
    }

    public boolean isUser() {
        return isUser;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
