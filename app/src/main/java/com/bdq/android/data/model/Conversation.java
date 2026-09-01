package com.bdq.android.data.model;

/**
 * 会话数据实体（Model�? * 对应一条会话记�? */
public class Conversation {
    private String id;
    private String title;       // 会话标题
    private String lastMessage; // 最后一条消�?    private long updatedAt;     // 最后更新时�?
    public Conversation(String id, String title, String lastMessage, long updatedAt) {
        this.id = id;
        this.title = title;
        this.lastMessage = lastMessage;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }
}
