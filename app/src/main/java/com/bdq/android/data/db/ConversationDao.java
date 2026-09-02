package com.bdq.android.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bdq.android.data.model.Conversation;

import java.util.List;

/**
 * 会话 DAO（Data Access Object）
 *
 * 类比 JPA Repository / MyBatis Mapper
 * 定义会话表的增删改查操作
 *
 * 面试考点：
 * - @Dao 标记这是数据访问接口
 * - @Insert / @Update / @Delete 自动生成实现
 * - @Query 写自定义 SQL
 * - 返回 LiveData 可以自动观察数据变化
 */
@Dao
public interface ConversationDao {

    /**
     * 查询所有会话，按更新时间倒序
     * 返回 LiveData：数据变化时自动通知观察者
     */
    @Query("SELECT * FROM conversations ORDER BY updatedAt DESC")
    LiveData<List<Conversation>> getAllConversations();

    /**
     * 插入一个新会话
     */
    @Insert
    void insertConversation(Conversation conversation);

    /**
     * 更新一个会话
     */
    @Update
    void updateConversation(Conversation conversation);

    /**
     * 删除一个会话
     */
    @Delete
    void deleteConversation(Conversation conversation);

    /**
     * 根据 id 查询会话
     */
    @Query("SELECT * FROM conversations WHERE id = :id")
    Conversation getConversationById(String id);
}
