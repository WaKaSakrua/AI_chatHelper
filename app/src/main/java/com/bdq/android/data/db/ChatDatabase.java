package com.bdq.android.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bdq.android.data.model.Conversation;
import com.bdq.android.data.model.Message;

/**
 * Room 数据库入口
 *
 * 类比 Spring Boot 的 DataSource 配置
 * 管理所有 Entity 和 DAO，是数据库的总入口
 *
 * 面试考点：
 * - @Database 标记这是数据库类，指定 entities 和 version
 * - 单例模式，整个 App 只有一个数据库实例
 * - Room.databaseBuilder() 构建数据库
 * - 数据库操作必须在子线程，不能在主线程（会报错）
 */
@Database(entities = {Conversation.class, Message.class}, version = 1)
public abstract class ChatDatabase extends RoomDatabase {

    // 单例实例
    private static volatile ChatDatabase INSTANCE;

    // 提供 DAO 的抽象方法，Room 自动生成实现
    public abstract ConversationDao conversationDao();
    public abstract MessageDao messageDao();

    /**
     * 获取数据库单例
     *
     * 双重检查锁模式的单例
     * 整个 App 只有一个数据库连接，避免资源浪费
     */
    public static ChatDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ChatDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ChatDatabase.class,
                            "chat_database"  // 数据库文件名
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
