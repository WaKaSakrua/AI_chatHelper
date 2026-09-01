package com.bdq.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bdq.android.data.model.Conversation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 会话列表�?ViewModel
 *
 * 作用�? * 1. 持有会话列表数据（屏幕旋转不丢失�? * 2. 处理业务逻辑（新建对话、加载数据）
 * 3. 通过 LiveData 把数据暴露给 View �? *
 * 注意：ViewModel 不持�?View 的引用，只暴露数�? */
public class MainViewModel extends ViewModel {

    // 内部�?MutableLiveData（可写）
    private final MutableLiveData<List<Conversation>> _conversations = new MutableLiveData<>();

    // 对外暴露 LiveData（只读），View 层只能观察不能改
    public LiveData<List<Conversation>> getConversations() {
        return _conversations;
    }

    /**
     * 加载会话列表
     * 现在是假数据，以后从 Repository �?     */
    public void loadConversations() {
        List<Conversation> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            list.add(new Conversation(
                    UUID.randomUUID().toString(),
                    "会话 " + i,
                    "这是�?" + i + " 条会话的最后一条消息内�?..",
                    System.currentTimeMillis() - i * 1000 * 60 * 10L
            ));
        }
        _conversations.setValue(list);
    }

    /**
     * 新建一个会�?     */
    public void createNewConversation() {
        List<Conversation> currentList = _conversations.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        Conversation newChat = new Conversation(
                UUID.randomUUID().toString(),
                "新对�?,
                "开始新的对话吧",
                System.currentTimeMillis()
        );

        // 加到列表最前面
        List<Conversation> newList = new ArrayList<>();
        newList.add(newChat);
        newList.addAll(currentList);

        // 更新 LiveData 的值，观察者会自动收到通知
        _conversations.setValue(newList);
    }
}
