package com.bdq.android.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 会话数据实体（Model 层）
 *
 * Room Entity：对应数据库中的 conversations 表
 * 类比 JPA 的 @Entity，一张表对应一个类
 *
 * 面试考点：
 * - @Entity 标记这是一个数据库表实体
 * - @PrimaryKey 标记主键
 * - 每个字段对应表中的一列
 */
@Entity(tableName = "conversations")
public class Conversation {

    @PrimaryKey
    private String id;              // 会话ID（主键）
    private String title;           // 会话标题
    private String lastMessage;     // 最后一条消息预览
    private long updatedAt;         // 最后更新时间戳

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

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
