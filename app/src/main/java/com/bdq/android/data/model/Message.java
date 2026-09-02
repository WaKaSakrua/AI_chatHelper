package com.bdq.android.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * 消息数据实体（Model 层）
 *
 * Room Entity：对应数据库中的 messages 表
 * 通过 ForeignKey 关联 conversations 表，会话删除时消息级联删除
 *
 * 面试考点：
 * - @ForeignKey 定义外键关联
 * - onDelete = CASCADE 级联删除
 * - 每个字段对应表中的一列
 */
@Entity(
    tableName = "messages",
    foreignKeys = @ForeignKey(
        entity = Conversation.class,
        parentColumns = "id",
        childColumns = "conversationId",
        onDelete = ForeignKey.CASCADE
    )
)
public class Message {

    @PrimaryKey
    private String id;                  // 消息ID（主键）
    private String conversationId;      // 所属会话ID（外键）
    private String content;             // 消息内容
    private boolean isUser;             // 是否是用户发送的
    private long timestamp;             // 发送时间戳

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

    public void setId(String id) {
        this.id = id;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
