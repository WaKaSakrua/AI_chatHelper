package com.bdq.android.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.bdq.android.data.model.Message;

import java.util.List;

/**
 * 消息 DAO（Data Access Object）
 *
 * 类比 JPA Repository / MyBatis Mapper
 * 定义消息表的增删改查操作
 *
 * 面试考点：
 * - @Dao 标记这是数据访问接口
 * - @Insert 自动生成插入实现
 * - @Query 写自定义 SQL
 * - 返回 LiveData 可以自动观察数据变化
 */
@Dao
public interface MessageDao {

    /**
     * 查询某个会话的所有消息，按时间正序
     * 返回 LiveData：数据变化时自动通知观察者
     */
    @Query("SELECT * FROM messages WHERE conversationId = :conversationId ORDER BY timestamp ASC")
    LiveData<List<Message>> getMessagesByConversation(String conversationId);

    /**
     * 插入一条新消息
     */
    @Insert
    void insertMessage(Message message);
}
